package com.buildledger.backend;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PushSqlBackup {

   // private static final String SQL_FILE_PATH = "src/main/resources/sql/dump.sql";
   // private static final String COMMIT_MESSAGE_PREFIX = "Backup ";
//
   // @Scheduled(fixedRate = 60000) // Изпълнява се на всеки 10 минути (600 000 ms)
   // public void backupAndPushSqlFile() {
   //     try {
   //         File sqlFile = new File(SQL_FILE_PATH);
   //         if (!sqlFile.exists()) {
   //             System.err.println("SQL dump file does not exist: " + SQL_FILE_PATH);
   //             return;
   //         }
//
   //         // Откриване на Git хранилището
   //         try (Git git = Git.open(new File("."))) {
   //             // Добавяне на файла в индекса
   //             git.add().addFilepattern(SQL_FILE_PATH).call();
//
   //             // Създаване на commit съобщение с дата и час
   //             String commitMessage = COMMIT_MESSAGE_PREFIX +
   //                     LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//
   //             // Създаване на commit
   //             git.commit().setMessage(commitMessage).call();
//
   //             // Пушване на промените в отдалеченото хранилище
   //             git.push()
   //                     .setCredentialsProvider(new UsernamePasswordCredentialsProvider("Daniel8Valkanov8", "Danchokebara12345"))
   //                     .call();
//
   //             System.out.println("Backup committed and pushed: " + commitMessage);
   //         }
   //     } catch (IOException | GitAPIException e) {
   //         System.err.println("Error during backup and push: " + e.getMessage());
   //         e.printStackTrace();
   //     }
   // }
}
