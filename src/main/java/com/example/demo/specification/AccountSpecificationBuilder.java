package com.example.demo.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Account;

public class AccountSpecificationBuilder {
	private final List<Criteria> params;

	public AccountSpecificationBuilder() {
		params = new ArrayList<>();
	}

	public AccountSpecificationBuilder with(final boolean orPredicate, final String key, final String operation,
			final Object value) {
		params.add(new Criteria(orPredicate, key, operation, value));
		return this;
	}

	public Specification<Account> build() {
		if (params.size() == 0) {
			return null;
		}

		final List<Specification> specs = params.stream().map(AccountSpecification::new)
				.collect(Collectors.toList());

		Specification result = specs.get(0);

		for (int i = 1; i < params.size(); i++) {
			result = params.get(i).isOrPredicate() ? Specification.where(result).or(specs.get(i))
					: Specification.where(result).and(specs.get(i));
		}
		return result;
	}
}
