package org.bighamapi.hmp.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bighamapi.hmp.service.PageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Content;
import com.rometools.rome.feed.rss.Item;

public class RSSFeedViewer extends AbstractRssFeedView {

    @Autowired
    private PageInfoService pageInfoService;

    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Channel feed, HttpServletRequest request) {
         PageInfo info = pageInfoService.getInfo();
         feed.setTitle(info.getTitle());
         feed.setDescription(info.getToTitle());
         feed.setLink(request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getRequestURI())));

         super.buildFeedMetadata(model, feed, request);
    }

    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
         List<RSSMessage> listContent = (List<RSSMessage>) model.get("feedContent");
         List<Item> items = new ArrayList<>(listContent.size());

         for(RSSMessage tempContent : listContent ){

              Item item = new Item();

              Content content = new Content();
              content.setValue(tempContent.getSummary());
              item.setContent(content);

              item.setTitle(tempContent.getTitle());
              item.setLink(tempContent.getUrl());
              item.setPubDate(tempContent.getCreatedDate());

              items.add(item);
         }
         response.setCharacterEncoding("UTF-8");
         return items;
    }
}