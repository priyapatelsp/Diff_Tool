package com.codingchallengePriya.myOwnDiffTool.controller;
import com.codingchallengePriya.myOwnDiffTool.service.LcsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
public class LcsController {

    @Autowired
    private LcsService lcsService;

    @PostMapping("/calculate")
    public String calculateLCS(@RequestParam(value = "string1", required = false) String string1,
                               @RequestParam(value = "string2", required = false) String string2,
                               @RequestParam(value = "file1", required = false) MultipartFile file1,
                               @RequestParam(value = "file2", required = false) MultipartFile file2,
                               Model model) {
        String resultString="";
        int resultLength=0;

        try {
            if (string1 != null && string2 != null && !string1.isBlank() && !string2.isBlank()) {
                resultString = lcsService.findLCS(string1, string2);
                resultLength = resultString.length();
            } else if (file1 != null && file2 != null) {
                String s1 = readFileContent(file1);
                String s2 = readFileContent(file2);
                resultString = lcsService.findLCS(s1, s2);
                resultLength = resultString.length();
            } else {
                resultString = "Invalid input. Please provide either two strings or two files.";
                resultLength = 0;
            }
        } catch (IOException e) {
            resultString = "Error processing files: " + e.getMessage();
            resultLength = 0;
        }

        model.addAttribute("resultString", resultString);
        model.addAttribute("resultLength", resultLength);
        return "index";
    }

    private String readFileContent(MultipartFile file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString().trim();
        }
    }
}
