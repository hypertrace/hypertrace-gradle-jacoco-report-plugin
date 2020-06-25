package org.hypertrace.gradle.jacoco;

import javax.annotation.Nonnull;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.UnknownTaskException;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.TaskProvider;
import org.gradle.api.tasks.testing.Test;
import org.gradle.testing.jacoco.tasks.JacocoReport;

public class JacocoReportPlugin implements Plugin<Project> {

  @Override
  public void apply(@Nonnull Project target) {
    configureJacocoReport(target);
  }

  private void configureJacocoReport(Project project) {
    project
        .getPluginManager()
        .withPlugin(
            "jacoco",
            unused ->
                project
                    .getTasks()
                    .withType(Test.class, test -> this.buildReportForTestTask(project, test)));
  }

  private void buildReportForTestTask(Project project, Test testTask) {
    String reportTaskName = String.format("jacoco%sReport", capitalize(testTask.getName()));
    try {
      // Existing reporting task
      project
          .getTasks()
          .named(reportTaskName, JacocoReport.class)
          .configure(jacocoReport -> this.configureExistingReport(jacocoReport, testTask));
    } catch (UnknownTaskException exception) {
      // Create new reporting task, doesn't exist
      this.createNewReport(project, reportTaskName, testTask)
          .configure(jacocoReport -> this.configureExistingReport(jacocoReport, testTask));
    }
  }

  private TaskProvider<JacocoReport> createNewReport(
      Project project, String reportTaskName, Test testTask) {
    return project
        .getTasks()
        .register(
            reportTaskName,
            JacocoReport.class,
            task -> {
              task.executionData(testTask);
              task.sourceSets(
                  project.getExtensions().getByType(SourceSetContainer.class).findByName("main"));
            });
  }

  private void configureExistingReport(JacocoReport reportTask, Test testTask) {
    reportTask.getReports().getXml().setEnabled(true);
    reportTask.dependsOn(testTask);
  }

  private String capitalize(String input) {
    return Character.toUpperCase(input.charAt(0)) + input.substring(1);
  }
}
