package com.machine_factotry.kursovaya.service;

import com.itextpdf.html2pdf.HtmlConverter;
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

    @Autowired
    SpringTemplateEngine templateEngine;
    @Autowired
    ReportServiceData serviceData;

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

        Context context = new Context();
        context.setVariables(data);
        String htmlContent = templateEngine
                .process("reports/manufacturing_report.html", context);

        return convertHtmlToPdf(htmlContent);
    }

    private byte[] convertHtmlToPdf(String htmlContent) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(htmlContent, outputStream);
        return outputStream.toByteArray();
    }

}
