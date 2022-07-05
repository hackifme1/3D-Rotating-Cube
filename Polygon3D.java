package com.miniproject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polygon3D
{
	private Color color;
	private Point3D[] points;

	public Polygon3D(Color color, Point3D... points)
	{
		this.color = color;
		this.points = new Point3D[points.length];
		for(int i=0;i<points.length;i++)
		{
			Point3D p = points[i];
			this.points[i]= new Point3D(p.x,p.y,p.z);
		}
	}
	
	public Polygon3D(Point3D... points)
	{
		this.color = Color.WHITE;
		this.points = new Point3D[points.length];
		for(int i=0;i<points.length;i++)
		{
			Point3D p = points[i];
			this.points[i]= new Point3D(p.x,p.y,p.z);
		}
	}
	
	public void render(Graphics g)
	{
		Polygon poly= new Polygon();
		for(int i=0;i<this.points.length;i++)
		{
			Point p = PointConverter.convertPoint(this.points[i]);
			poly.addPoint(p.x, p.y);
		}
		g.setColor(this.color);
		g.fillPolygon(poly);
	}
	
	public void rotate(double xDegrees, double yDegrees, double zDegrees )
	{
		for(Point3D p: points)
		{
			PointConverter.rotateAxisX(p, xDegrees);
			PointConverter.rotateAxisY(p, yDegrees);
     		PointConverter.rotateAxisZ(p, zDegrees);
		}
	}
	
	public void setColor(Color color)
	{
		this.color=color;
	}
	
	public double getAvgX()
	{
	    double sum=0;
	    for(Point3D p: this.points)
	    {
		    sum+=p.x;
	    }
	    return sum/this.points.length;
    }
	
   public static Polygon3D[] sortPolygons(Polygon3D[] polygon)
	{
		List<Polygon3D> polygonsList = new ArrayList<Polygon3D>();
		
		for(Polygon3D poly: polygon)
		{
			polygonsList.add(poly);
		}

		Collections.sort(polygonsList, (p1, p2) -> p2.getAvgX()-p1.getAvgX()<0?1:-1);

		for(int i=0;i<polygon.length;i++)
		{
			polygon[i]= polygonsList.get(i);
		}
		return polygon;
	}
}
