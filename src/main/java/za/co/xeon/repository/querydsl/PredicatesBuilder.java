package za.co.xeon.repository.querydsl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysema.query.types.expr.BooleanExpression;
/**
 * Created by Derick on 7/9/2016.
 */
public class PredicatesBuilder {
    private final List<SearchCriteria> params;
    private final Class type;
    private final String variable;
    private final Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");

    public PredicatesBuilder(Class type, String variable) {
        params = new ArrayList<SearchCriteria>();
        this.type = type;
        this.variable = variable;
    }

    public PredicatesBuilder search(String search) {
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            this.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        return this;
    }

    public PredicatesBuilder with(final String key, final String operation, final Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public BooleanExpression build() {
        if (params.size() == 0) {
            return null;
        }

        final List<BooleanExpression> predicates = new ArrayList<BooleanExpression>();
        QueryPredicate predicate;
        for (final SearchCriteria param : params) {
            predicate = new QueryPredicate(param, type, variable);
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
