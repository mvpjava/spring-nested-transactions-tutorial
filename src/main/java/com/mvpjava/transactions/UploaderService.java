package com.mvpjava.transactions;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UploaderService {

	public void upload(List<String> sql);

}
