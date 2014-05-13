/*
 * Brief: Interface for "Comment" class implementation
 * Author: Olivia Bradley-Willemann
 * Date: 3/2014
 * Test cases: See ServiceTest.java
 * Major Mods: n/a
 * */

package com.vcclass.app.Repository;

import com.vcclass.app.Data.Comment;
import javax.sql.DataSource;

public interface CommentDAO 
{
	public void setDataSource(DataSource ds);
	public int CreateComment(int userId, Comment comment);
	public Comment GetComment(int commentId, int userId, int questionId);
	public boolean UpdateComment(Comment comment);
	public boolean DeleteComment(int userId, int commentId);
}