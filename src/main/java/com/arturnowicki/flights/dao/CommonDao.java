package com.arturnowicki.flights.dao;

import java.util.List;
import java.util.Optional;

public interface CommonDao {
	public <T> T add(T t);
	public <T> void update(T t);
	public <T> void delete(T t);
	public <T> Optional<T> getById(int id);
	public <T> List<T> getAll(final Class<T> type);
}
