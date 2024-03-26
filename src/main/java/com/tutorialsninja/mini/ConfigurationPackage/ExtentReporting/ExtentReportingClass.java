package com.tutorialsninja.mini.ConfigurationPackage.ExtentReporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentReportingClass {
    public ExtentReports generateEctentReport() {
        ExtentReports extentReports = new ExtentReports();
        File extentReportFile = new File("TestOutputs/ExtentReporting/ExtentReport.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

        sparkReporter.config().setReportName("TutorialsNinja Extent Report");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Extent Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy");

        extentReports.attachReporter(sparkReporter);

        return extentReports;
    }
}
