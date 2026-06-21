package com.moisegit.todolist.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class Utils {

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    // TUDO QUE TIVER NULO VAI SER ATRIBUÍDO DENTRO DO BEANUTILS COPY
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source); //src é uma variável que guarda uma ferramenta (BeanWrapper) para inspecionar os campos do objeto source.

        PropertyDescriptor[] pds =  src.getPropertyDescriptors(); //Essa linha pega todos os campos do objeto e guarda numa lista para poder analisar depois.
        Set<String> emptyNames = new HashSet<>(); //Essa linha cria uma lista (sem repetição) para guardar os nomes dos campos que estão nulos.
        for(PropertyDescriptor pd : pds){
           Object srcValue = src.getPropertyValue(pd.getName());
           if(srcValue == null) emptyNames.add(pd.getName());
        }
        String [] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
