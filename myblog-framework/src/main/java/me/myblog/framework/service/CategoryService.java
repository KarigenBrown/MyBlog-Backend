package me.myblog.framework.service;

import me.myblog.framework.domain.jooq.tables.pojos.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategory();
}

