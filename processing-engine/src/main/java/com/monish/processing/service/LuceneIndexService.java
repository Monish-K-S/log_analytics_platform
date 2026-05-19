package com.monish.processing.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;
import org.springframework.stereotype.Service;
import com.monish.processing.entity.LogEntity;
import org.apache.lucene.document.Field;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.document.StringField;
    @Service
    public class LuceneIndexService{
        private final Directory directory = new ByteBuffersDirectory();
        private final Analyzer analyzer = new StandardAnalyzer();

        public void indexLog(LogEntity logEntity)throws IOException{
        Document document = new Document();
        document.add(new TextField("service", logEntity.getService(), Field.Store.YES));
        document.add(new TextField("message", logEntity.getMessage(), Field.Store.YES));
        document.add(new StringField("level", logEntity.getLevel(), Field.Store.YES));

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(directory, config);
        writer.addDocument(document);
        writer.close();


        }
        public List<Map<String,String>> searchLogs(String query)throws Exception {
            List<Map<String, String>> results = new ArrayList<>();
            IndexReader reader = DirectoryReader.open(directory);
            IndexSearcher searcher =  new IndexSearcher(reader);

            QueryParser parser = new QueryParser("message", analyzer);
            Query luceneQuery = parser.parse(query);

            TopDocs topDocs = searcher.search(luceneQuery,10);

            for(ScoreDoc scoreDoc : topDocs.scoreDocs){
                Document document = searcher.doc(scoreDoc.doc);
                Map<String, String> logResult = new HashMap<>();
                logResult.put("service", document.get("service"));
                logResult.put("level",document.get("level"));
                logResult.put("message", document.get("message"));
                results.add(logResult);
            }
            reader.close();
            return results;
     }
}
