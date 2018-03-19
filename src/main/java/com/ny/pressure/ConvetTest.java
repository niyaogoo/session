package com.ny.pressure;

import org.apache.commons.lang.math.IntRange;
import org.springframework.beans.BeanUtils;

import java.util.stream.IntStream;

public class ConvetTest {

    public static void main(String[] args) {
        long st = System.currentTimeMillis();
        IntStream.range(0, 10000).forEach(i -> {
            Entity entity = new Entity();
            Data data = new Data();
            //BeanUtils.copyProperties(entity, data);
            data.setA(entity.getA());
            data.setB(entity.getB());
            data.setC(entity.getC());
            data.setD(entity.getD());
            data.setE(entity.getE());
            data.setF(entity.getF());
            data.setId(entity.getId());
            data.setName(entity.getName());
            data.setPassword(entity.getPassword());

        });
        System.out.println(System.currentTimeMillis() - st);
    }

    static class Entity {
        private String id = "id";
        private String name = "name";
        private String password = "password";
        private String a = "a";
        private String b = "b";
        private String c = "c";
        private String d = "d";
        private String e = "e";
        private String f = "f";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }

        public String getE() {
            return e;
        }

        public void setE(String e) {
            this.e = e;
        }

        public String getF() {
            return f;
        }

        public void setF(String f) {
            this.f = f;
        }
    }

    static class Data {
        private String id = "id";
        private String name = "name";
        private String password = "password";
        private String a = "a";
        private String b = "b";
        private String c = "c";
        private String d = "d";
        private String e = "e";
        private String f = "f";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }

        public String getE() {
            return e;
        }

        public void setE(String e) {
            this.e = e;
        }

        public String getF() {
            return f;
        }

        public void setF(String f) {
            this.f = f;
        }
    }
}
