import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@UsePlaywright
public class DownloadAssignment extends BaseSetup{
    @Test
    public void download() throws InterruptedException {
        int timeout =30;
        int elapsedTime =0;
        String donwloadFolderPath = System.getProperty("user.dir") + File.separator + "JenkinsDownload";

        File jenkinsDownloadDir = new File(donwloadFolderPath);
        if (!jenkinsDownloadDir.exists()) {
            System.out.println("Jenkins Folder not found");
            if (jenkinsDownloadDir.mkdir()) {
                System.out.println("Folder Created");
            }
        }
        setUp("");
        Download download = page.waitForDownload(() -> {
            // Trigger the download via JS, since there's no link to click
            page.evaluate("url => window.location.href = url",
                    "https://get.jenkins.io/war-stable/2.516.3/jenkins.war");
        });
        // Save the downloaded file
        download.saveAs(Paths.get(donwloadFolderPath));
        // check if the file has been downloaded successfully
        File jenkinsFile = new File(jenkinsDownloadDir,"jenkins.war");
        while (!jenkinsFile.exists() && elapsedTime<timeout) {
            Thread.sleep(1000);
            elapsedTime++;
            System.out.println("waiting for file to download");
        }
        if(jenkinsFile.exists()){
            System.out.println("File is downloaded successfully");
        }
        else {
            System.out.println("File is not downloaded!!");
        }
    }
    }
