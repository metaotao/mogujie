package com.tao.mogujie.parser;

import org.jsoup.nodes.Document;

public interface NetConnection {
    public Document getDocument(String url);

}
