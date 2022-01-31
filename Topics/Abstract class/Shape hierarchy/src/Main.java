
abstract class Shape {

    abstract double getPerimeter();

    abstract double getArea();
}

class Triangle extends Shape {
    private double a;
    private double b;
    private double c;

    Triangle(double a, double b, double c) {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    double getPerimeter() {
        return a + b + c;
    }

    @Override
    double getArea() {
        double half = (a + b + c) / 2;
        return Math.sqrt(half * (half - a) * (half - b) * (half - c));
    }
}

class Rectangle extends Shape {

    private double a;
    private double b;

    Rectangle(double a, double b) {
        super();
        this.a = a;
        this.b = b;
    }

    @Override
    double getPerimeter() {
        return 2 * (a + b);
    }

    @Override
    double getArea() {
        return a * b;
    }
}

class Circle extends  Shape {

    private double radius;

    Circle(double radius) {
        super();
        this.radius = radius;
    }

    @Override
    double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}