package com.codingchallengePriya.myOwnDiffTool.controller;

import com.codingchallengePriya.myOwnDiffTool.service.LcsResult;
import com.codingchallengePriya.myOwnDiffTool.service.LcsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LcsController {

    private final LcsService lcsService;

    public LcsController(LcsService lcsService) {
        this.lcsService = lcsService;
    }

    @GetMapping("/lcs")
    public LcsResponse getLcsLength(@RequestParam String str1, @RequestParam String str2) {
        LcsResult result = lcsService.calculateLcs(str1, str2);
        return new LcsResponse(result.getLength(), result.getLcsString());
    }
}

// Add a response class to encapsulate both length and LCS string
class LcsResponse {
    private final int length;
    private final String lcsString;

    public LcsResponse(int length, String lcsString) {
        this.length = length;
        this.lcsString = lcsString;
    }

    public int getLength() {
        return length;
    }

    public String getLcsString() {
        return lcsString;
    }
}