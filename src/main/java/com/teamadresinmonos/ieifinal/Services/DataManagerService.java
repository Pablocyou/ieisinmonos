package com.teamadresinmonos.ieifinal.Services;

import com.teamadresinmonos.ieifinal.Extractor.ExtractorCAT;
import com.teamadresinmonos.ieifinal.Extractor.ExtractorCV;
import com.teamadresinmonos.ieifinal.Extractor.ExtractorMUR;
import org.springframework.stereotype.Service;

@Service
public class DataManagerService {

    public static int dunkCAT(String filename){
        return ExtractorCAT.dunk(filename);
    }

    public static int dunkMUR(String filename){
        try {
            return ExtractorMUR.dunk(filename);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int dunkCV(String filename){
        try {
            return ExtractorCV.dunk(filename);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}