package com.crud.tasks.scheduler;


import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private DbService dbService;

    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *") // once a day at 10 AM
//    @Scheduled(fixedDelay = 10_000) // once every 10 seconds
    public void sendInformationEmail() {
        long size = dbService.getAllTasks().size();
        simpleEmailService.sendDailyMail(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Currently in database you got: " + size + (size > 1 ? " tasks" : " task"),
                "paulaszabat@gmail.com")
        );
    }
}
