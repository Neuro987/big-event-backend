package com.learn.bigevent.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    private Long total; // Total number of records
    private List<T> items; // List of records for the current page, T is the type of Article in use.
}
