package com.board.entity;

import org.apache.commons.collections4.map.ListOrderedMap;
import org.springframework.jdbc.support.JdbcUtils;

public class CamelMap extends ListOrderedMap<String, Object> {

   private static final long serialVersionUID = 1L;

   @Override
   public Object put(String key, Object value) {
      if (key.indexOf('_') < 0 && Character.isLowerCase(key.charAt(0)))
         return super.put(key, value);

      return super.put(JdbcUtils.convertUnderscoreNameToPropertyName((String) key), value);
   }
}
