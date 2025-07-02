package com.machine_factotry.kursovaya.service;

import com.itextpdf.html2pdf.HtmlConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportsService {

    private static final Logger log = LoggerFactory.getLogger(ReportsService.class);
    private final SpringTemplateEngine templateEngine;
    private final ReportServiceData serviceData;

    public ReportsService(SpringTemplateEngine templateEngine, ReportServiceData serviceData) {
        this.templateEngine = templateEngine;
        this.serviceData = serviceData;
    }

    public byte[] generateManufacturingReport() throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("date", serviceData.reportDate());
        data.put("productionVolume", serviceData.productionVolume());
        data.put("marketableProducts", serviceData.marketableProducts());
        data.put("laborPerformance", serviceData.laborPerformance());
        data.put("planLaborPerformance", serviceData.planLaborPerformance());
        data.put("planProductionVolume", serviceData.planProductionVolume());
        data.put("planMarketableProducts", serviceData.planMarketableProducts());
        data.put("planProductionVolumeComplete", serviceData.planProductionVolumeComplete());
        data.put("planMarketableProductsComplete", serviceData.planMarketableProductsComplete());
        data.put("getNomenclaturePlan", serviceData.getNomenclaturePlan());
        data.put("getSalaryDepartmentSum", serviceData.getSalaryDepartmentSum());
        data.put("getEquipmentStatusReport", serviceData.getEquipmentStatusReport());
        data.put("getDeliveryReport", serviceData.getDeliveryReport());
        data.put("getSalesReport", serviceData.getSalesReport());


        Context context = new Context();
        context.setVariables(data);
        String htmlContent = templateEngine
                .process("reports/manufacturing_report.html", context);

        log.info("Generating manufacturing report...");
        return convertHtmlToPdf(htmlContent);
    }

    private byte[] convertHtmlToPdf(String htmlContent) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(htmlContent, outputStream);
        return outputStream.toByteArray();
    }

}
