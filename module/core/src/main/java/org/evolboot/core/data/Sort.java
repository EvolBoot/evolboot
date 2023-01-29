
package org.evolboot.core.data;

import org.evolboot.core.util.Assert;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.MethodInvocationRecorder;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Deprecated
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Sort implements Serializable {

    private static final long serialVersionUID = 5737186511678863905L;

    private static final Sort UNSORTED = Sort.by(new Sort.Order[0]);

    public static final Sort.Direction DEFAULT_DIRECTION = Sort.Direction.ASC;

    private final List<Sort.Order> orders;


    /**
     * Creates a new {@link Sort} instance.
     *
     * @param direction  defaults to {@link Sort#DEFAULT_DIRECTION} (for {@literal null} cases, too)
     * @param properties must not be {@literal null} or contain {@literal null} or empty strings.
     */
    private Sort(Sort.Direction direction, List<String> properties) {

        if (properties == null || properties.isEmpty()) {
            throw new IllegalArgumentException("You have to provide at least one property to sort by!");
        }

        this.orders = properties.stream() //
                .map(it -> new Sort.Order(direction, it)) //
                .collect(Collectors.toList());
    }

    /**
     * Creates a new {@link Sort} for the given properties.
     *
     * @param properties must not be {@literal null}.
     * @return
     */
    public static Sort by(String... properties) {

        Assert.notNull(properties, "Properties must not be null!");

        return properties.length == 0 //
                ? Sort.unsorted() //
                : new Sort(DEFAULT_DIRECTION, Arrays.asList(properties));
    }

    /**
     * Creates a new {@link Sort} for the given {@link Sort.Order}s.
     *
     * @param orders must not be {@literal null}.
     * @return
     */
    public static Sort by(List<Sort.Order> orders) {

        Assert.notNull(orders, "Orders must not be null!");

        return orders.isEmpty() ? Sort.unsorted() : new Sort(orders);
    }

    /**
     * Creates a new {@link Sort} for the given {@link Sort.Order}s.
     *
     * @param orders must not be {@literal null}.
     * @return
     */
    public static Sort by(Sort.Order... orders) {

        Assert.notNull(orders, "Orders must not be null!");

        return new Sort(Arrays.asList(orders));
    }

    /**
     * Creates a new {@link Sort} for the given {@link Sort.Order}s.
     *
     * @param direction  must not be {@literal null}.
     * @param properties must not be {@literal null}.
     * @return
     */
    public static Sort by(Sort.Direction direction, String... properties) {

        Assert.notNull(direction, "Direction must not be null!");
        Assert.notNull(properties, "Properties must not be null!");
        Assert.isTrue(properties.length > 0, "At least one property must be given!");

        return Sort.by(Arrays.stream(properties)//
                .map(it -> new Sort.Order(direction, it))//
                .collect(Collectors.toList()));
    }

    /**
     * Creates a new {@link Sort.TypedSort} for the given type.
     *
     * @param type must not be {@literal null}.
     * @return
     * @since 2.2
     */
    public static <T> Sort.TypedSort<T> sort(Class<T> type) {
        return new Sort.TypedSort<>(type);
    }

    /**
     * Returns a {@link Sort} instances representing no sorting setup at all.
     *
     * @return
     */
    public static Sort unsorted() {
        return UNSORTED;
    }

    /**
     * Returns a new {@link Sort} with the current setup but descending order direction.
     *
     * @return
     */
    public Sort descending() {
        return withDirection(Sort.Direction.DESC);
    }

    /**
     * Returns a new {@link Sort} with the current setup but ascending order direction.
     *
     * @return
     */
    public Sort ascending() {
        return withDirection(Sort.Direction.ASC);
    }

    public boolean isSorted() {
        return !orders.isEmpty();
    }

    public boolean isUnsorted() {
        return !isSorted();
    }


    /*
     * (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<Sort.Order> iterator() {
        return this.orders.iterator();
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(@Nullable Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Sort)) {
            return false;
        }

        Sort that = (Sort) obj;

        return this.orders.equals(that.orders);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int result = 17;
        result = 31 * result + orders.hashCode();
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return orders.isEmpty() ? "UNSORTED" : StringUtils.collectionToCommaDelimitedString(orders);
    }

    /**
     * Creates a new {@link Sort} with the current setup but the given order direction.
     *
     * @param direction
     * @return
     */
    private Sort withDirection(Sort.Direction direction) {

        return Sort.by(orders.stream().map(it -> new Sort.Order(direction, it.getProperty())).collect(Collectors.toList()));
    }

    /**
     * Enumeration for sort directions.
     *
     * @author Oliver Gierke
     */
    public enum Direction {

        ASC, DESC;

        /**
         * Returns whether the direction is ascending.
         *
         * @return
         * @since 1.13
         */
        public boolean isAscending() {
            return this.equals(ASC);
        }

        /**
         * Returns whether the direction is descending.
         *
         * @return
         * @since 1.13
         */
        public boolean isDescending() {
            return this.equals(DESC);
        }

        /**
         * Returns the {@link Sort.Direction} enum for the given {@link String} value.
         *
         * @param value
         * @return
         * @throws IllegalArgumentException in case the given value cannot be parsed into an enum value.
         */
        public static Sort.Direction fromString(String value) {

            try {
                return Sort.Direction.valueOf(value.toUpperCase(Locale.US));
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format(
                        "Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).", value), e);
            }
        }

        /**
         * Returns the {@link Sort.Direction} enum for the given {@link String} or null if it cannot be parsed into an enum
         * value.
         *
         * @param value
         * @return
         */
        public static Optional<Sort.Direction> fromOptionalString(String value) {

            try {
                return Optional.of(fromString(value));
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }
        }
    }

    /**
     * Enumeration for null handling hints that can be used in {@link Sort.Order} expressions.
     *
     * @author Thomas Darimont
     * @since 1.8
     */
    public enum NullHandling {

        /**
         * Lets the data store decide what to do with nulls.
         */
        NATIVE,

        /**
         * A hint to the used data store to order entries with null values before non null entries.
         */
        NULLS_FIRST,

        /**
         * A hint to the used data store to order entries with null values after non null entries.
         */
        NULLS_LAST
    }

    /**
     * PropertyPath implements the pairing of an {@link Sort.Direction} and a property. It is used to provide input for
     * {@link Sort}
     *
     * @author Oliver Gierke
     * @author Kevin Raymond
     */
    public static class Order implements Serializable {

        private static final long serialVersionUID = 1522511010900108987L;
        private static final boolean DEFAULT_IGNORE_CASE = false;
        private static final Sort.NullHandling DEFAULT_NULL_HANDLING = Sort.NullHandling.NATIVE;

        private final Sort.Direction direction;
        private final String property;
        private final boolean ignoreCase;
        private final Sort.NullHandling nullHandling;

        /**
         * Creates a new {@link Sort.Order} instance. if order is {@literal null} then order defaults to
         * {@link Sort#DEFAULT_DIRECTION}
         *
         * @param direction can be {@literal null}, will default to {@link Sort#DEFAULT_DIRECTION}
         * @param property  must not be {@literal null} or empty.
         */
        public Order(@Nullable Sort.Direction direction, String property) {
            this(direction, property, DEFAULT_IGNORE_CASE, DEFAULT_NULL_HANDLING);
        }

        /**
         * Creates a new {@link Sort.Order} instance. if order is {@literal null} then order defaults to
         * {@link Sort#DEFAULT_DIRECTION}
         *
         * @param direction        can be {@literal null}, will default to {@link Sort#DEFAULT_DIRECTION}
         * @param property         must not be {@literal null} or empty.
         * @param nullHandlingHint must not be {@literal null}.
         */
        public Order(@Nullable Sort.Direction direction, String property, Sort.NullHandling nullHandlingHint) {
            this(direction, property, DEFAULT_IGNORE_CASE, nullHandlingHint);
        }

        /**
         * Creates a new {@link Sort.Order} instance. Takes a single property. Direction defaults to
         * {@link Sort#DEFAULT_DIRECTION}.
         *
         * @param property must not be {@literal null} or empty.
         * @since 2.0
         */
        public static Sort.Order by(String property) {
            return new Sort.Order(DEFAULT_DIRECTION, property);
        }

        /**
         * Creates a new {@link Sort.Order} instance. Takes a single property. Direction is {@link Sort.Direction#ASC} and
         * NullHandling {@link Sort.NullHandling#NATIVE}.
         *
         * @param property must not be {@literal null} or empty.
         * @since 2.0
         */
        public static Sort.Order asc(String property) {
            return new Sort.Order(Sort.Direction.ASC, property, DEFAULT_NULL_HANDLING);
        }

        /**
         * Creates a new {@link Sort.Order} instance. Takes a single property. Direction is {@link Sort.Direction#DESC} and
         * NullHandling {@link Sort.NullHandling#NATIVE}.
         *
         * @param property must not be {@literal null} or empty.
         * @since 2.0
         */
        public static Sort.Order desc(String property) {
            return new Sort.Order(Sort.Direction.DESC, property, DEFAULT_NULL_HANDLING);
        }

        /**
         * Creates a new {@link Sort.Order} instance. if order is {@literal null} then order defaults to
         * {@link Sort#DEFAULT_DIRECTION}
         *
         * @param direction    can be {@literal null}, will default to {@link Sort#DEFAULT_DIRECTION}
         * @param property     must not be {@literal null} or empty.
         * @param ignoreCase   true if sorting should be case insensitive. false if sorting should be case sensitive.
         * @param nullHandling must not be {@literal null}.
         * @since 1.7
         */
        private Order(@Nullable Sort.Direction direction, String property, boolean ignoreCase, Sort.NullHandling nullHandling) {

            if (!StringUtils.hasText(property)) {
                throw new IllegalArgumentException("Property must not null or empty!");
            }

            this.direction = direction == null ? DEFAULT_DIRECTION : direction;
            this.property = property;
            this.ignoreCase = ignoreCase;
            this.nullHandling = nullHandling;
        }

        /**
         * Returns the order the property shall be sorted for.
         *
         * @return
         */
        public Sort.Direction getDirection() {
            return direction;
        }

        /**
         * Returns the property to order for.
         *
         * @return
         */
        public String getProperty() {
            return property;
        }

        /**
         * Returns whether sorting for this property shall be ascending.
         *
         * @return
         */
        public boolean isAscending() {
            return this.direction.isAscending();
        }

        /**
         * Returns whether sorting for this property shall be descending.
         *
         * @return
         * @since 1.13
         */
        public boolean isDescending() {
            return this.direction.isDescending();
        }

        /**
         * Returns whether or not the sort will be case sensitive.
         *
         * @return
         */
        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        /**
         * Returns a new {@link Sort.Order} with the given {@link Sort.Direction}.
         *
         * @param direction
         * @return
         */
        public Sort.Order with(Sort.Direction direction) {
            return new Sort.Order(direction, this.property, this.ignoreCase, this.nullHandling);
        }

        /**
         * Returns a new {@link Sort.Order}
         *
         * @param property must not be {@literal null} or empty.
         * @return
         * @since 1.13
         */
        public Sort.Order withProperty(String property) {
            return new Sort.Order(this.direction, property, this.ignoreCase, this.nullHandling);
        }

        /**
         * Returns a new {@link Sort} instance for the given properties.
         *
         * @param properties
         * @return
         */
        public Sort withProperties(String... properties) {
            return Sort.by(this.direction, properties);
        }

        /**
         * Returns a new {@link Sort.Order} with case insensitive sorting enabled.
         *
         * @return
         */
        public Sort.Order ignoreCase() {
            return new Sort.Order(direction, property, true, nullHandling);
        }

        /**
         * Returns a {@link Sort.Order} with the given {@link Sort.NullHandling}.
         *
         * @param nullHandling can be {@literal null}.
         * @return
         * @since 1.8
         */
        public Sort.Order with(Sort.NullHandling nullHandling) {
            return new Sort.Order(direction, this.property, ignoreCase, nullHandling);
        }

        /**
         * Returns a {@link Sort.Order} with {@link Sort.NullHandling#NULLS_FIRST} as null handling hint.
         *
         * @return
         * @since 1.8
         */
        public Sort.Order nullsFirst() {
            return with(Sort.NullHandling.NULLS_FIRST);
        }

        /**
         * Returns a {@link Sort.Order} with {@link Sort.NullHandling#NULLS_LAST} as null handling hint.
         *
         * @return
         * @since 1.7
         */
        public Sort.Order nullsLast() {
            return with(Sort.NullHandling.NULLS_LAST);
        }

        /**
         * Returns a {@link Sort.Order} with {@link Sort.NullHandling#NATIVE} as null handling hint.
         *
         * @return
         * @since 1.7
         */
        public Sort.Order nullsNative() {
            return with(Sort.NullHandling.NATIVE);
        }

        /**
         * Returns the used {@link Sort.NullHandling} hint, which can but may not be respected by the used datastore.
         *
         * @return
         * @since 1.7
         */
        public Sort.NullHandling getNullHandling() {
            return nullHandling;
        }

        /*
         * (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {

            int result = 17;

            result = 31 * result + direction.hashCode();
            result = 31 * result + property.hashCode();
            result = 31 * result + (ignoreCase ? 1 : 0);
            result = 31 * result + nullHandling.hashCode();

            return result;
        }

        /*
         * (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(@Nullable Object obj) {

            if (this == obj) {
                return true;
            }

            if (!(obj instanceof Sort.Order)) {
                return false;
            }

            Sort.Order that = (Sort.Order) obj;

            return this.direction.equals(that.direction) && this.property.equals(that.property)
                    && this.ignoreCase == that.ignoreCase && this.nullHandling.equals(that.nullHandling);
        }

        /*
         * (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {

            String result = String.format("%s: %s", property, direction);

            if (!Sort.NullHandling.NATIVE.equals(nullHandling)) {
                result += ", " + nullHandling;
            }

            if (ignoreCase) {
                result += ", ignoring case";
            }

            return result;
        }
    }

    /**
     * Extension of Sort to use method handles to define properties to sort by.
     *
     * @author Oliver Gierke
     * @soundtrack The Intersphere - Linger (The Grand Delusion)
     * @since 2.2
     */
    public static class TypedSort<T> extends Sort {

        private static final long serialVersionUID = -3550403511206745880L;

        private final MethodInvocationRecorder.Recorded<T> recorded;

        private TypedSort(Class<T> type) {
            this(MethodInvocationRecorder.forProxyOf(type));
        }

        private TypedSort(MethodInvocationRecorder.Recorded<T> recorded) {

            super(Collections.emptyList());
            this.recorded = recorded;
        }

        public <S> TypedSort<S> by(Function<T, S> property) {
            return new TypedSort<>(recorded.record(property));
        }

        public <S> TypedSort<S> by(MethodInvocationRecorder.Recorded.ToCollectionConverter<T, S> collectionProperty) {
            return new TypedSort<>(recorded.record(collectionProperty));
        }

        public <S> TypedSort<S> by(MethodInvocationRecorder.Recorded.ToMapConverter<T, S> mapProperty) {
            return new TypedSort<>(recorded.record(mapProperty));
        }

        @Override
        public Sort ascending() {
            return withDirection(Sort::ascending);
        }

        @Override
        public Sort descending() {
            return withDirection(Sort::descending);
        }

        private Sort withDirection(Function<Sort, Sort> direction) {

            return recorded.getPropertyPath() //
                    .map(Sort::by) //
                    .map(direction) //
                    .orElseGet(Sort::unsorted);
        }

        /*
         * (non-Javadoc)
         * @see Sort#iterator()
         */
        @Override
        public Iterator<Order> iterator() {

            return recorded.getPropertyPath() //
                    .map(Order::by) //
                    .map(Collections::singleton) //
                    .orElseGet(Collections::emptySet).iterator();

        }

        /*
         * (non-Javadoc)
         * @see Sort#toString()
         */
        @Override
        public String toString() {

            return recorded.getPropertyPath() //
                    .map(Sort::by) //
                    .orElseGet(Sort::unsorted) //
                    .toString();
        }
    }

    public org.springframework.data.domain.Sort toJpaSort() {
        return org.springframework.data.domain.Sort.by(
                this.getOrders().stream()
                        .map(aOrder -> aOrder.isAscending()
                                ? org.springframework.data.domain.Sort.Order.asc(aOrder.getProperty())
                                : org.springframework.data.domain.Sort.Order.desc(aOrder.getProperty())
                        )
                        .toArray(org.springframework.data.domain.Sort.Order[]::new));
    }
}