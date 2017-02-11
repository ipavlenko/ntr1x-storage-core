package com.ntr1x.storage.core.services;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class ScaleImageService implements IScaleImageService {

    @Override
    public BufferedImage scale(BufferedImage source, Type type, Integer width, Integer height) throws IOException {
        
        Rectangle bounds = bounds(
            type,
            new Dimension(source.getWidth(), source.getHeight()),
            new Dimension(width == null ? -1 : width, height == null ? -1 : height)
        );
        
        Image scaled = source.getScaledInstance(
            bounds.width,
            bounds.height,
            Image.SCALE_SMOOTH
        );

        BufferedImage target = new BufferedImage(
            width == null ? scaled.getWidth(null) : width,
            height == null ? scaled.getHeight(null) : height,
            BufferedImage.SCALE_DEFAULT
        );
        
        target.getGraphics().setColor(Color.WHITE);
        target.getGraphics().fillRect(0, 0, target.getWidth(), target.getHeight());
        
        target.getGraphics().drawImage(
            scaled,
            bounds.x,
            bounds.y,
            bounds.width < 0 ? target.getWidth() : bounds.width,
            bounds.height < 0 ? target.getHeight() : bounds.height,
            null
        );
        
        return target;
    }
    
    private Rectangle bounds(Type type, Dimension source, Dimension target) {
    	
        if (target.width < 0 && target.height < 0)
            return new Rectangle(0, 0, source.width, source.height);
        if (target.width < 0)
        	return type == Type.LIMIT
        		? new Rectangle(0, 0, -1, Math.min(target.height, source.height))
				: new Rectangle(0, 0, -1, target.height)
			;
        if (target.height < 0) {
            return type == Type.LIMIT
        		? new Rectangle(0, 0, Math.min(target.width, source.width), -1)
				: new Rectangle(0, 0, target.width, -1)
			;
        }
        
        if (type == Type.LIMIT) {
        	
        	if (source.width <= target.width && source.height <= target.height) {
        		return new Rectangle(0, 0, source.width, source.height);
        	}
        }
        
        switch (type) {
        
        	case LIMIT: 
            case CONTAIN: {
                
                float kw = target.width / (float) source.width;
                float kh = target.height / (float) source.height;
                
                return kw < kh
                    ? new Rectangle(0, (int) ((target.height - kw * source.height) / 2.f), target.width, (int) (kw * source.height))
                    : new Rectangle((int) ((target.width - kh * source.width) / 2.f), 0, (int) (kh * source.width), target.height)
                ;
            }
            case COVER: {
                
                float kw = target.width / (float) source.width;
                float kh = target.height / (float) source.height;
                
                return kw > kh
                    ? new Rectangle(0, (int) ((target.height - kw * source.height) / 2.f), target.width, (int) (kw * source.height))
                    : new Rectangle((int) ((target.width - kh * source.width) / 2.f), 0, (int) (kh * source.width), target.height)
                ;
            }
            case SCALE:
            default: {
                return new Rectangle(0, 0, target.width, target.height);
            }
        }
    }
}