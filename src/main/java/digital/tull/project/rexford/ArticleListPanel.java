/*
 * Copyright 2018 William Jackson.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package digital.tull.project.rexford;

import digital.tull.project.rexford.data.Article;
import digital.tull.project.rexford.data.Persistence;
import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

public class ArticleListPanel extends Panel
{
	private static final long serialVersionUID = 1L;

	public ArticleListPanel(String id)
    {
        super(id);
        
        final List<Article> articleList = Application.get().getMetaData(WicketApplication.articleListKey);
        
        add(new ListView<Article>("articles", articleList)
        {
            @Override
            protected void populateItem(final ListItem<Article> item)
            {
                //PropertyModel articleTitle = new PropertyModel(item.getModelObject(), "title");
                
                final ArticlePage articlePage = new ArticlePage();
                
                articlePage.setDefaultModel(new CompoundPropertyModel(item.getModelObject()));
                
                //final ArticleFormPage editArticleFormPage = new ArticleFormPage(
                //        new PageParameters().add("transaction", StringValue.valueOf("EDIT")));
                
                //editArticleFormPage.setDefaultModel(new CompoundPropertyModel(item.getModelObject()));
                
                //final ArticleFormPage deleteArticleFormPage = new ArticleFormPage(
                //        new PageParameters().add("transaction", StringValue.valueOf("DELETE")).add("articleTitle", item.getModelObject().getTitle()));
                
                //deleteArticleFormPage.setDefaultModel(new CompoundPropertyModel(item.getModelObject()));
                //deleteArticleFormPage.add(new PropertyModel(item.getModelObject(), "title"));
                
                final Link link = new Link<Void>("titleLink")
                {
                    @Override
                    public void onClick()
                    {
                        setResponsePage(articlePage);
                    }
                };
                
//                final Link edit = new Link<Void>("editLink")
//                {
//                    @Override
//                    public void onClick()
//                    {
//                        setResponsePage(editArticleFormPage);
//                    }
//                };
                
//                final Link delete = new Link<Void>("deleteLink")
//                {
//                    @Override
//                    public void onClick()
//                    {
//                        setResponsePage(deleteArticleFormPage);
//                    }
//                };
                
                
                
                //delete.add(new Label("delete", "Delete"));
                
                //edit.add(new Label("edit", "Edit"));
                
                link.add(new Label("title", new PropertyModel(item.getModelObject(), "title")));
                
                //item.add(edit);
                //item.add(delete);
                
                item.add(link);
                item.add(new Label("caption", new PropertyModel(item.getModelObject(), "caption")));
                item.add(new Label("date", new PropertyModel(item.getModelObject(), "date")));
            }
        });
        
        //Article nullArticle = null;
        
        //final ArticleFormPage newArticleFormPage = new ArticleFormPage(new PageParameters().add("transaction", StringValue.valueOf("NEW")));
   		//newArticleFormPage.setDefaultModel(new CompoundPropertyModel(new Article()));
        
//        final Link link = new Link<Void>("newLink")
//        {
//            @Override
//            public void onClick()
//            {
//                setResponsePage(newArticleFormPage);
//            }
//        };
        
        //link.add(new Label("new", "New Article..."));
        
        //add(link);
    }
}
