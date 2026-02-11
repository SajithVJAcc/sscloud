package com.example.sscloud.service;

import com.example.sscloud.config.SalaryConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryService {
    private final SalaryConfig salaryConfig;

    public SalaryService(SalaryConfig salaryConfig) {
        this.salaryConfig = salaryConfig;
    }

    public String getSalaryRange(int level) {
        List<String> ranges = salaryConfig.getRanges();
        if (level > 0 && level <= ranges.size()) {
            return ranges.get(level - 1); // level 1 → first range
        }
        return "Level not found";
    }

    public Integer getRangeCount() {
        return salaryConfig.getRanges().size();
    }

}
