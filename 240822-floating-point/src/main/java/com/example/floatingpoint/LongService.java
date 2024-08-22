package com.example.floatingpoint;

import org.springframework.stereotype.Service;

@Service
public class LongService {
    
    public long getValueOutOfRange() {
        return 71250203184799758L;
    }

    public long getValueInRange() {
        return 1000L;
    }
}
