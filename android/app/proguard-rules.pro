# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\sean.shi\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-dontnote org.apache.http.**
-dontnote android.net.http.**
-dontnote com.google.vending.licensing.ILicensingService
-dontnote com.android.vending.licensing.ILicensingService
-dontnote android.support.v4.app.NotificationCompatJellybean
-dontnote android.support.v4.text.ICUCompatApi23
-dontnote android.support.v4.text.ICUCompatIcs
-dontnote android.support.v7.widget.DrawableUtils

-keep class android.support.design.** {*;}
-keep class android.support.v4.** {*;}
-keep class android.support.v7.** {*;}

#------------- log4j ------------------
-dontnote org.apache.log4j.**
-dontwarn org.apache.log4j.**

#------------- spongcastle ------------------
-dontnote org.spongycastle.**
-dontwarn org.spongycastle.x509.util.LDAPStoreHelper
-dontwarn org.spongycastle.pqc.jcajce.provider.**
-dontwarn org.spongycastle.jce.spec.**
-dontwarn org.spongycastle.jce.provider.**
-dontwarn org.spongycastle.jce.ECKeyUtil
