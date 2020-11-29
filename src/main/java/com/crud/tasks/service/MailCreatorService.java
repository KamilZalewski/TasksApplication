package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending task to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        context.setVariable("goodbye_message","Thank You!");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_goal", companyConfig.getCompanyGoal());
        context.setVariable("company_email", companyConfig.getCompanyEmail());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        context.setVariable("preview_message", "Good Morning!");
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildTaskQuantityDailyEmail(String message) {
        List<String> effectiveTaskManagementTips = new ArrayList<>();
        effectiveTaskManagementTips.add("Make To-do Lists");
        effectiveTaskManagementTips.add("Prioritize");
        effectiveTaskManagementTips.add("Schedule");
        effectiveTaskManagementTips.add("Be Flexible");
        effectiveTaskManagementTips.add("Manage Change");
        effectiveTaskManagementTips.add("Delegate");
        effectiveTaskManagementTips.add("Be Involved");
        effectiveTaskManagementTips.add("Be Patient");
        effectiveTaskManagementTips.add("Communicate");
        effectiveTaskManagementTips.add("Use Task Management Software");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Your Application Frontend");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Bye, till tomorrow. Your application AI");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_goal", companyConfig.getCompanyGoal());
        context.setVariable("company_email", companyConfig.getCompanyEmail());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("effective_task_management_tips", effectiveTaskManagementTips);
        context.setVariable("tasks_url2", "https://www.ntaskmanager.com/blog/task-management-skills/");
        context.setVariable("button2", "Visit website - 10 Tips for Effective Task Management");

        return templateEngine.process("mail/task_quantity_daily_mail", context);
    }

}
