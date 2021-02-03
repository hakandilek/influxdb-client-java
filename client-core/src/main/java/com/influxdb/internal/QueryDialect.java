/*
 * The MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.influxdb.internal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class QueryDialect {

  public static final QueryDialect DEFAULT_DIALECT = new DialectBuilder()
      .header(true)
      .delimiter(",")
      .quoteChar("\"")
      .commentPrefix("#")
      .annotations(new String[]{"datatype", "group", "default"})
      .getDialect();

  public static final QueryDialect EMPTY_DIALECT = new QueryDialect();

  private final transient Gson gson = new GsonBuilder()
      .create();

  private String quoteChar;
  private String commentPrefix;
  private String delimiter;
  private boolean header;
  private String[] annotations;

  QueryDialect() {
  }

  @Override
  public String toString() {
    if (this == EMPTY_DIALECT) return null;
    return gson.toJson(this);
  }

  private static class DialectBuilder {

    private final QueryDialect dialect;

    public DialectBuilder() {
      this.dialect = new QueryDialect();
    }

    public DialectBuilder header(boolean header) {
      dialect.header = header;
      return this;
    }

    public DialectBuilder delimiter(String delimiter) {
      dialect.delimiter = delimiter;
      return this;
    }

    public DialectBuilder quoteChar(String quoteChar) {
      dialect.quoteChar = quoteChar;
      return this;
    }

    public DialectBuilder commentPrefix(String commentPrefix) {
      dialect.commentPrefix = commentPrefix;
      return this;
    }

    public DialectBuilder annotations(String[] annotations) {
      dialect.annotations = annotations;
      return this;
    }

    public QueryDialect getDialect() {
      return dialect;
    }
  }
}
