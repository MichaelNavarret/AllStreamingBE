package com.example.demo.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Account;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import static com.example.demo.util.SpecificationOperation.EQUALS;
import static com.example.demo.util.SpecificationOperation.LIKE;


@SuppressWarnings("serial")
public class AccountSpecification implements Specification<Account>{
	
	private Criteria criteria;
	
	public AccountSpecification() {
		
	}
	
	
	
	public AccountSpecification(Criteria criteria) {
		super();
		this.criteria = criteria;
	}
	
	@Override
    public Specification<Account> and(Specification<Account> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Account> or(Specification<Account> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase(EQUALS)) {
            return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
        }

        if (criteria.getOperation().equalsIgnoreCase(LIKE)) {
            return criteriaBuilder.like(root.get(criteria.getKey()),"%" + criteria.getValue().toString() + "%");
        }
        return null;
    }
  }
