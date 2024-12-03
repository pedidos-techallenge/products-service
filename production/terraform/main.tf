terraform {
  backend "s3" {
    bucket = "techchallenge-customers-bucket"
    region = "us-east-1"
    key    = "mysql.tfstate"
  }
}

provider "aws" {
  region = "us-east-1"
}

### General data sources
data "aws_vpc" "techchallenge-vpc" {
  filter {
    name   = "tag:Name"
    values = ["techchallenge-vpc"]
  }
}

data "aws_iam_role" LabRole {
  name = "LabRole"
}

data "aws_subnets" "private-subnets" {
  filter {
    name   = "vpc-id"
    values = [data.aws_vpc.techchallenge-vpc.id]
  }

  filter {
    name   = "tag:Name"
    values = ["*private*"]
  }
}