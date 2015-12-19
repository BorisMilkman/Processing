float sphereRadius = 50;

int rand;
void setup() {
  size(1000, 1000, P3D);
  background(0);
  
  camera(140.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
       
  createSpherePoints();
}

void createSpherePoints() {
  for (int i = 0; i < 1500; i++) {
    SpherePoint.addInstance((int) random(8), random(0.02, 0.02), (int) random(150), random(0.02, 0.02), sphereRadius, random(3) + 1);
    SpherePoint.addInstance((int) random(8) + 45, random(0.02, 0.02), (int) random(150), random(0.02, 0.02), sphereRadius, random(3) + 1);
    SpherePoint.addInstance((int) random(8) + 90, random(0.02, 0.02), (int) random(150), random(0.02, 0.02), sphereRadius, random(3) + 1);
    SpherePoint.addInstance((int) random(8) + 135, random(0.02, 0.02), (int) random(150), random(0.02, 0.02), sphereRadius, random(3) + 1);
    
    SpherePoint.addInstance((int) random(8) + 180, random(0.02, 0.02), (int) random(150) + 0, random(0.02, 0.02), sphereRadius, random(3) + 1);
    SpherePoint.addInstance((int) random(8) + 225, random(0.02, 0.02), (int) random(150) + 0, random(0.02, 0.02), sphereRadius, random(3) + 1);
    SpherePoint.addInstance((int) random(8) + 270, random(0.02, 0.02), (int) random(150) + 0, random(0.02, 0.02), sphereRadius, random(3) + 1);
    SpherePoint.addInstance((int) random(8) + 315, random(0.02, 0.02), (int) random(150) + 0, random(0.02, 0.02), sphereRadius, random(3) + 1);
  }
}

void draw() {
  rand = (int) random(100);
  
  clear();
  noFill();
  
  beginCamera();
    rotateY(0.01);
    rotateX(0.01);
    rotateZ(0.01);
  endCamera();
  
  strokeWeight(0.03);
  
  if (rand < 2){ 
    stroke(255, 0, 0);
    strokeWeight(0.3);   
  }
  
  sphere(45);
  
  if (rand < 20) {
    strokeWeight(0.08);
    sphere(42);
  }
  
  SpherePoint.updateInstances();
  
  drawSpherePpoints();
}

void drawSpherePpoints(){
  noFill();
  
  for (int i = 0; i < SpherePoint.list.size(); i++){
    
    if ((int) random(100) < 50) {
      if (rand >= 2) {
        strokeWeight(SpherePoint.list.get(i).strokeWidth);
        stroke(255);
        
        int rand1 = (int) random(300);
        
        if ((int) rand1 == 1) stroke(255, 0, 0);
        
        point(SpherePoint.list.get(i).x, SpherePoint.list.get(i).y, SpherePoint.list.get(i).z);
        
        strokeWeight(0.005);
        
        if ((int) rand1 == 1) strokeWeight(0.2);
        
        line(SpherePoint.list.get(i).x, SpherePoint.list.get(i).y, SpherePoint.list.get(i).z, 0, 0, 0);
      }else {
        strokeWeight(SpherePoint.list.get(i).strokeWidth);
        stroke(255, 0, 0);
        point(SpherePoint.list.get(i).x, SpherePoint.list.get(i).y, SpherePoint.list.get(i).z);
        
        strokeWeight(random(0.01, 0.01));
        line(SpherePoint.list.get(i).x, SpherePoint.list.get(i).y, SpherePoint.list.get(i).z, 0, 0, 0);
      }
    }
  }
}

static class SpherePoint {
  static ArrayList<SpherePoint> list = new ArrayList<SpherePoint>();
  
  float angle1, angle2;
  float sphereRadius;
  
  float strokeWidth;
  
  float x, y, z;
  
  float angle1movement, angle2movement;
  
  static SpherePoint addInstance(float angle1, float angle1movement, float angle2, float angle2movement, float sphereRadius, float strokeWidth) {
    SpherePoint sp = new SpherePoint(angle1, angle1movement, angle2, angle2movement, sphereRadius, strokeWidth);
    list.add(sp);
    
    return sp;
  }
  
  SpherePoint (float angle1, float angle1movement, float angle2, float angle2movement, float sphereRadius, float strokeWidth) {
    this.angle1 = radians(angle1);
    this.angle1movement = angle1movement;
    
    this.angle2 = radians(angle2);
    this.angle2movement = angle2movement;
    
    this.sphereRadius = sphereRadius;
    this.strokeWidth = strokeWidth;
    
    calculateCartesianCoords();
  }
  
  void calculateCartesianCoords() {
    this.x = this.sphereRadius * sin(angle1) * cos(angle2);
    this.y = this.sphereRadius * cos(angle1);
    this.z = this.sphereRadius * sin(angle1) * sin(angle2);
  }
  
  static void updateInstances() {
    for (int i = 0; i < list.size(); i++) {
      list.get(i).update();
    }
  }
  
  void update() {
    angle1 += angle1movement;
    angle2 += angle2movement;
    
    if (angle1 >= 360) angle1 = 0;
    if (angle2 >= 360) angle2 = 0;
    
    calculateCartesianCoords();
  }
}
