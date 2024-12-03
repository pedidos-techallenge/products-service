### RDS subnets
# Referencing private subnets
data "aws_subnets" "private" {
  filter {
    name   = "tag:kubernetes.io/role/internal-elb"
    values = ["1"]
  }

  filter {
    name   = "vpc-id"
    values = [data.aws_vpc.techchallenge-vpc.id]
  }
}

# RDS subnets must be in a sg group
resource "aws_db_subnet_group" "rds_subnets" {
  name       = "rds_subnets"
  subnet_ids = data.aws_subnets.private.ids
}

# Creating RDS instance
resource "aws_db_instance" "rds_db" {
  engine               = "mysql"
  identifier           = "techchallenge-customers-service"
  allocated_storage    = 20
  engine_version       = "8.0.35"
  instance_class       = "db.t3.small"
  username             = var.MYSQL_USERNAME
  password             = var.MYSQL_PASSWORD
  skip_final_snapshot  = true
  publicly_accessible  = false
  multi_az             = false
  db_name              = "dbtechchallange"
  port                 = 3306
  db_subnet_group_name = aws_db_subnet_group.rds_subnets.name
  vpc_security_group_ids = [
    aws_security_group.rds-eks-sg.id,
    aws_security_group.rds-lambda-sg.id
  ]
}