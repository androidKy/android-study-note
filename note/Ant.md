# Apache Ant

[TOC]

## Ant的定义

Apache Ant,是一个将软件编译、测试、部署等步骤联系在一起加以自动化的一个工具，大多用于Java环境中的软件开发。

## Ant的作用

Ant的主要目的就是把我们想做的事情自动化，通过写脚本Ant文件（默认执行文件为build.xml）即可编译生成一个项目。

## Ant的运行原理

### Ant配置

1. 在Ant安装之前需要安装好JDK，并配置好JAVA_HOME；
2. 新建环境变量ANT_HOME：值为ANT的主目录；
3. 在path中配置%ANT_HOME%/bin；
4. 配置完成之后，打开命令行，输入ant，当出现“Buildfile: build.xml does not exist! Build failed”时说明配置完成；

### Ant 入门

1. ant的默认生成文件为build.xml； 

2. 输入ant后，ant会在当前目录下搜索是否有build.xml，如果有，则执行；

3. 当然也可以自定义生成文件，通过ant -f a.xml即可指定a.xml为生成文件；  ant的生成文件是xml文件，整体结构为： 

   ```xml
   <?xml version="1.0" encoding="GBK"?>  
   <project default="targetname" basedir="." name="ant">  
       <target name="name">  
       </target>  
       <target name="name1">
       </target>
   </project>
   ```

   **project**是生成文件的根元素，表示一个工程；

   **target**是project的子元素，表示一个任务；

   一个project中可以定义多个target元素，表示多个任务； 

   **default**属性表示默认执行的target，如果ant命令没有指定target的name，则执行default的target；  

   **basedir**属性表示在当前执行文件的路径下；

   **name**属性表示工程名字；

   **ant targetname**则会执行此target，而忽略default设置的target；target的name不能重复。 

## Ant的语法格式

```xml
<?xml version="1.0" encoding="utf-8"?>
<project default="targetname" basedir="." name="ant">
    <property name="pname" value="pvalue" file="a.properties"/>	
    <!-- name和value表示一个Map映射集合，使用${pname}=pvalue。
 		file属性表示通过此属性文件导入属性。
		如果单纯想使用$，则通过$$表示。
	-->
    <condition else="1.0.2" property="version_name" value="${version_name}">
        <isset property="version_name" />
    </condition>
    
	<path id="libraries">	<!-- id属性表示编号，用于被引用 -->  
        <pathelement location="${sdk.libraryjars}"/>
        <fileset dir="${dir.sdk}/libs">
            <include name="**/*.jar"/>
        </fileset>
    </path>
    
    <tstamp prefix="buildtime">
        <format pattern="yyMMddHHmm" property="TimeSign" />
        <format pattern="yyMMddHH" property="TimeSign2"/>
    </tstamp>
     <!--
		时间戳,用法${buildTime.TimeSign}表示当前时间1808171041。
	-->
    
	<target name="targetName" depends="targetA" if="prop1" unless="prop2"> 
	<!-- depends表示执行此target会先执行targetA完成之后才执行此target。
 		if表示只有设置了该属性名才能执行此target。
		unless表示只有没有此属性名才能执行此target -->
        
    	<echo>打印输出</echo>
        <echo append="false" file="${dir.common.release}/SampleProject/settings.gradle"
            message="include ':Sample'${line.separator}" />
        <!-- 
			append:message的内容是否添加在后面。
			file:如果文件不存在会自动创建。
			message:在file种写入内容。
			${line.separator}表示换行符。
		-->
        
        <javac srcdir="srcdir" destdir="classdir" classpath="thirdjar"/>
        <!-- javac编译java文件变成字节码class文件.
			srcdir：编译的Java文件所在的路径。
			destdir:编译后的class文件所在的路径。
			classpath:依赖的第三方类库。
		-->
        
        <java classname="javaname" fork="yes">
        	<arg line="param1 param2 param3"/>
        </java>
        <!--
			classname用于指定运行的类名称； 
			fork=”yes”表示另起一个JVM来执行java命令，而不是中断ANT命令，因此fork必须为yes；
		-->
        
        <jar destfile="jarname.jar" basedir="${classdir}">
        	 <manifest>
       			 <attribute name="Main-Class" value="classname"/>  <!--指定主类-->
        	 </manifest>
        </jar>
        <!--
			destfiie的值为jar包的名称，一般为${dest}/main.jar； 
			basedir的值是需要打成jar包的目录，一般为${classes}； 
			manifest表示设置META-INF；
		-->
        
        <mkdir dir="a\b"/>	<!-- 创建目录a\b,"\"也可以为"/" -->
        
        <delete dir="a\b"/>	<!-- 删除a目录下的b目录 -->
        <delete file="a.txt"/>	<!-- 删除a.txt文件 -->
        
        <copy file="${filedir}/file1.jar" tofile="${destdir}/file2.jar"/>	<!-- 复制文件 -->
        <copy encoding="utf-8" todir="${dir.common.release}/Sample">
            <fileset dir="${dir.sample}">
                <exclude name="**/build/**" />
                <exclude name="**/target/**" />
                <exclude name="**/*.iml" />
                <exclude name="**/.gradle/**" />
                <exclude name="**/.idea/**" />
                <exclude name="**/third_source_sign.jks" />
            </fileset>
            <!--  <fileset dir="${dir.files}/debug">
                  	<include name="build.gradle" />
            	  </fileset>-->
        </copy>
        
        <move file="${filedir}/file.apk" tofile="${destfiledir}/destfile.apk"/>
        <!-- 移动文件。并不是复制，而是把文件移动到别的地方，并且可以重命名。
			file是源文件。tofile是目标文件。
		-->
        
        <replace file="filename" token="old" value="new" />
        <replaceregexp encoding="utf-8"
            file="${dir.sdk}/src/main/java/com/bat/source/util/LogUtil.java"
            match="debug\s+=\s+false" replace="debug = true" />
        <replace dir="${dir.sdk}" encoding="utf-8" token="//LogUtil.info"
            value="LogUtil.info">
            <include name="**/*.java" />
            <exclude name="**/LogUtil.java" />
        </replace>
        <!-- 替换文件的内容
			file表示要执行替换的文件； 
			token表示被替换的字符串； 
			value表示替换的字符串。
		-->
        <echo>给${gradlew}赋予执行权限</echo>
        <chmod file="${dir}/${gradlew}" perm="ugo+x"/> 
        <exec executable="./${gradlew}">
            <arg line="ThirdSourceSDK:buildSDK" />
            <arg line="ThirdSourceSDK:assembleRelease"/>
        </exec>
		<!-- 执行ThirdSourceSDK项目下的build.gradle文件的task:buildSDK和assembleRelease。 -->    
    </target>
        
   	<target name="targetA">
    	<ant antfile="build_sub.xml" dir="${projectBaseDir}"/>
        <!-- ant表示执行另一个ant执行文件,antfile表示执行的脚本，dir表示执行的项目的基路径。 -->

        <antcall target="targetB" />
        <!-- antcall表示要执行另一个targetB。 -->
    </target>
    
	<!-- 以下节点path、dirset、fileslist、fileset、zip都必须在target中 -->
   
    
	<dirset dir="lib">	<!-- dir指定根路径 -->
         <include name="**/build/**"/>
    	<include name="**/*.class"/>
        <include name="**/.class" />
	</dirset>
    
    <filelist dir="lib" files="a.xml,b.xml"/> <!-- files指定哪些文件 -->
        
	<fileset dir="lib">
		<exclude name="**/build/**" />
		<exclude name="**/target/**" />
         <exclude name="**/*.iml" />
         <exclude name="**/.gradle/**" />
         <exclude name="**/.idea/**" />
         <exclude name="**/third_source_sign.jks" />  
         <include name="**/build/**"/>
    	 <include name="**/*.class"/>
         <include name="**/.class" />
	</fileset>
    
    <zip 					 			      destfile="${dir}/thirdsource_sdk_fb_v${version_name}_release_${buildtime.TimeSign}.zip"  encoding="utf-8">
            <zipfileset dir="${dir.common.release}">
                <exclude name="**/*.apk" />
                <exclude name="**/**debug.jar" />
            </zipfileset>
	</zip>	   <!-- 把文件压缩成zip包。 -->
 	
</project>
```

## Ant的标签

|      标签       |       说明        |              属性              |                            子标签                            |
| :-------------: | :---------------: | :----------------------------: | :----------------------------------------------------------: |
|     project     |       项目        |     name、basedir、default     |               `property、target、tstamp、path`               |
|  **property**   |       变量        |       name、value、file        |                                                              |
|   **target**    |     执行任务      |   name、depends、if、unless    | `property、ant、antcall、echo、chmod、copy、delete、mkdir、replace、replaceregexp、move、exec、javac、zip、java、jar` |
|   **tstamp**    |       时间        |             prefix             |                 `format :pattern、property`                  |
|    **path**     |     资源路径      |               id               |       `pathelement : location、fileset :dir(include)`        |
|      *ant*      | 执行另一个ant脚本 |          antfile、dir          |                                                              |
|    *antcall*    | 执行另一个target  |             target             |                                                              |
|     *echo*      | 输出或者创建文件  |     append、file、message      |                                                              |
|     *chmod*     |     赋予权限      |           file、perm           |                                                              |
|     *copy*      |     复制文件      | file、tofile、encoding、todir  |               `fileset:dir(exclude、include)`                |
|    *delete*     |     删除文件      |           file、dir            |                                                              |
|      mkdir      |    创建文件夹     |              dir               |                                                              |
|      move       |     移动文件      |          file、tofile          |                                                              |
|     replace     |     替换字符      |  encoding、dir、token、value   |                `include：name、exclude:name`                 |
| *replaceregexp* |     替换字符      | encoding、file、match、replace |                                                              |
|     *exec*      |     执行命令      |           executable           |                          `arg:line`                          |
|      *zip*      |       压缩        |       encoding、destfile       |              `zipfileset:dir(exclude、include)`              |
|     fileset     |     文件集合      |              dir               |                 `exclude:name、include:name`                 |
|     include     |       包含        |              name              |                                                              |
|     exclude     |      不包含       |              name              |                                                              |

