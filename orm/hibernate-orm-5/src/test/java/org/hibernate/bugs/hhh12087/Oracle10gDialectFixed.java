/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.bugs.hhh12087;

import java.util.Locale;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.Oracle10gDialect;

/**
 *
 * @author Rustem Yagudin <rustem.yagudin@t-systems.com>
 * This is fixed dialect which is provides correct pagination
 */
public class Oracle10gDialectFixed extends Dialect {
  /**
   * Constructs a Bis Oracle10gDialect
   */
  public Oracle10gDialectFixed() {
    super();
  }

  @Override
  public String getLimitString(String sql, boolean hasOffset) {
    sql = sql.trim();
    String forUpdateClause = null;
    boolean isForUpdate = false;
    final int forUpdateIndex = sql.toLowerCase(Locale.ROOT).lastIndexOf( "for update");
    if ( forUpdateIndex > -1 ) {
      // save 'for update ...' and then remove it
      forUpdateClause = sql.substring( forUpdateIndex );
      sql = sql.substring( 0, forUpdateIndex-1 );
      isForUpdate = true;
    }

    final StringBuilder pagingSelect = new StringBuilder( sql.length() + 100 );
    pagingSelect.append( "select * from ( select row_.*, rownum rownum_ from ( " );
    pagingSelect.append( sql );
    if (hasOffset) {
      pagingSelect.append( " ) row_ ) where rownum_ <= ? and rownum_ > ? " );
    }
    else {
      pagingSelect.append( " ) row_ ) where rownum_ <= ?" );
    }

    if ( isForUpdate ) {
      pagingSelect.append( " " );
      pagingSelect.append( forUpdateClause );
    }

    return pagingSelect.toString();
  }
}
