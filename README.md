## 集成
#### 1.在根项目的build.gradle文件中添加：
   ```
   buildscript {
       dependencies {
           classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.10'
       }
   }

   allprojects {
       repositories {
          maven { url 'https://jitpack.io' }
       }
   }
   ```

#### 2.在app模块的build.gradle文件中添加：
  ```
  apply plugin: 'android-aspectjx'
  
  dependencies {
       implementation 'com.github.siren4:Click:2.0.0'
  }
  ```
## 文档
#### https://github.com/siren4/Click/wiki
