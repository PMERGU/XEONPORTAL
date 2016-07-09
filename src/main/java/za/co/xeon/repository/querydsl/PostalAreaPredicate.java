package za.co.xeon.repository.querydsl;

import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathBuilder;
import com.mysema.query.types.path.StringPath;
import za.co.xeon.domain.PostalArea;

/**
 * Created by Derick on 7/9/2016.
 */
public class PostalAreaPredicate {
    private SearchCriteria criteria;

    public PostalAreaPredicate() {
    }

    public PostalAreaPredicate(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public BooleanExpression getPredicate() {
        PathBuilder<PostalArea> entityPath = new PathBuilder<PostalArea>(PostalArea.class, "postalArea");

        if (isNumeric(criteria.getValue().toString())) {
            NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
            int value = Integer.parseInt(criteria.getValue().toString());
            if (criteria.getOperation().equalsIgnoreCase(":")) {
                return path.eq(value);
            }
            else if (criteria.getOperation().equalsIgnoreCase(">")) {
                return path.goe(value);
            }
            else if (criteria.getOperation().equalsIgnoreCase("<")) {
                return path.loe(value);
            }
        }
        else {
            StringPath path = entityPath.getString(criteria.getKey());
            if (criteria.getOperation().equalsIgnoreCase(":")) {
                return path.containsIgnoreCase(criteria.getValue().toString());
            }
        }
        return null;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(final SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public static boolean isNumeric(final String str) {
        try {
            Integer.parseInt(str);
        } catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }
}
