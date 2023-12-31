package cn.locusc.camelinaction.c2;

import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;

public class OrderFileFilter<T> implements GenericFileFilter<T> {

    @Override
    public boolean accept(GenericFile<T> file) {
        return file.getFileName().endsWith("csv");
    }

}
