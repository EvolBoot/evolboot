package org.evolboot.identity.domain.user.repository.jpa.convert;

import org.evolboot.shared.lang.UserIdentity;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.SqlTypes;
import org.hibernate.usertype.UserTypeSupport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * @author evol
 */
public class UserIdentitySetConverterForUserType extends UserTypeSupport<Set<UserIdentity>> {

    public UserIdentitySetConverterForUserType() {
        // 对应的Java类型和数据库中的类型
        super(Integer.class, SqlTypes.INTEGER);
    }

    @Override
    public Set<UserIdentity> nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) throws SQLException {
        // 读取回来
        return UserIdentity.convertEnum(rs.getInt(position));
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Set<UserIdentity> value, int index, SharedSessionContractImplementor session) throws SQLException {
        // 保存回去
        int symbol = UserIdentity.convertToSymbol(value);
        st.setInt(index, symbol);
    }
}
