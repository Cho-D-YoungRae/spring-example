package com.example.enumcode;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@RequestMapping("/code")
@RestController
@RequiredArgsConstructor
public class CodeController {

    private final EnumMapperFactory enumMapperFactory;

    @GetMapping
    public ResponseEntity<Map<String, List<EnumMapperValue>>> codeList(
            @RequestParam(required = false) List<String> codeTypes) {
        if (isNull(codeTypes)) {
            return ResponseEntity.ok(enumMapperFactory.getAll());
        }
        return ResponseEntity.ok(enumMapperFactory.get(codeTypes));
    }
}
