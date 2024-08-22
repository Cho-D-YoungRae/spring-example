package com.example.floatingpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LongController {

    private final LongService longService;

    public LongController(LongService longService) {
        this.longService = longService;
    }

    @ResponseBody
    @GetMapping("/api/value-in-range")
    public LongResponse getValueInRange() {
        return new LongResponse(longService.getValueInRange());
    }

    @ResponseBody
    @GetMapping("/api/value-in-range-as-string")
    public StringResponse getValueInRangeAsString() {
        return new StringResponse(String.valueOf(longService.getValueInRange()));
    }

    @GetMapping("/value-in-range")
    public String valueInRangePage() {
        return "value-in-range";
    }

    @ResponseBody
    @GetMapping("/api/value-out-of-range")
    public LongResponse getValueOutOfRange() {
        return new LongResponse(longService.getValueOutOfRange());
    }

    @ResponseBody
    @GetMapping("/api/value-out-of-range-as-string")
    public StringResponse getValueOutOfRangeAsString() {
        return new StringResponse(String.valueOf(longService.getValueOutOfRange()));
    }

    @GetMapping("/value-out-of-range")
    public String valueOutOfRangePage() {
        return "value-out-of-range";
    }
}
