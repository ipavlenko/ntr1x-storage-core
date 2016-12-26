package com.ntr1x.storage.core.services;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface IScaleImageService {
    
    public static enum Type {
        COVER,
        CONTAIN,
        SCALE,
        
        ;
    }

    BufferedImage scale(BufferedImage source, Type type, int width, int height) throws IOException;
}
