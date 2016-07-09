package za.co.xeon.repository.querydsl;

import java.util.ArrayList;
import java.util.List;

import com.mysema.query.types.expr.BooleanExpression;
/**
 * Created by Derick on 7/9/2016.
 */
public class PostalAreaPredicatesBuilder    {
    private final List<SearchCriteria> params;

    public PostalAreaPredicatesBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public PostalAreaPredicatesBuilder with(final String key, final String operation, final Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public BooleanExpression build() {
        if (params.size() == 0) {
            return null;
        }

        final List<BooleanExpression> predicates = new ArrayList<BooleanExpression>();
        PostalAreaPredicate predicate;
        for (final SearchCriteria param : params) {
            predicate = new PostalAreaPredicate(param);
            final BooleanExpression exp = predicate.getPredicate();
            if (exp != null) {
                predicates.add(exp);
            }
        }

        BooleanExpression result = predicates.get(0);
        for (int i = 1; i < predicates.size(); i++) {
            result = result.and(predicates.get(i));
        }
        return result;
    }
}
