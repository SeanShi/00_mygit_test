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

-dontobfuscate

# Keep source file and line numbers for better crash logs
-keepattributes SourceFile,LineNumberTable

#------------ system ------------------
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

#------------- IQ ------------------
-keep class com.iqmetrix.pos.** {*;}

#------------- log4j ------------------
-dontwarn org.apache.log4j.**

#------------- spongcastle ------------------
-dontnote org.spongycastle.**
-dontwarn org.spongycastle.x509.util.LDAPStoreHelper
-dontwarn org.spongycastle.pqc.jcajce.provider.**
-dontwarn org.spongycastle.jce.spec.**
-dontwarn org.spongycastle.jce.provider.**
-dontwarn org.spongycastle.jce.ECKeyUtil
-keep class org.spongycastle.jce.provider.**
-keep class org.spongycastle.jcajce.provider.asymmetric.ec.**
-keep class org.spongycastle.crypto.**
-keep class org.spongycastle.**{*;}

#------------- injection -------------------
-keepattributes *Annotation*
-keepclassmembers,allowobfuscation class * {
    @javax.inject.* *;
    @dagger.* *;
    <init>();
}
-keep class **$$ModuleAdapter
-keep class **$$InjectAdapter
-keep class **$$StaticInjection
-keepnames class dagger.Lazy

#------------ event bus --------------------
-keepattributes *Annotation*
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

#------------ call back ------------------
-keep class rba_sdk.RBA_API{ *;}
-keep class rbasdk_android_adapter.NetworkAdapter {*;}
-keep class rbasdk_android_adapter.BluetoothAdapter {*;}
-keep class rba_sdk.Comm_Settings {*;}

#------------- itext -------------------
-dontwarn com.itextpdf.**
-keep class com.itextpdf.**
-keep class java.awt.geom.**

#------------- mail ---------------------
-dontwarn org.apache.harmony.awt.datatransfer.**
-dontwarn org.apache.harmony.awt.ContextStorage
-dontwarn com.sun.mail.imap.protocol.**
-dontwarn  javax.activation.CommandInfo

-keep class javamail.** {*;}
-keep class javax.mail.** {*;}
-keep class javax.activation.** {*;}

-keep class com.sun.mail.dsn.** {*;}
-keep class com.sun.mail.handlers.** {*;}
-keep class com.sun.mail.smtp.** {*;}
-keep class com.sun.mail.util.** {*;}
-keep class mailcap.** {*;}
-keep class mimetypes.** {*;}
-keep class myjava.awt.datatransfer.** {*;}
-keep class org.apache.harmony.awt.** {*;}
-keep class org.apache.harmony.misc.** {*;}


#------------- other ------------------
-dontwarn dagger.internal.codegen.**
-dontwarn com.google.common.**
-dontwarn com.squareup.javawriter.JavaWriter

#------------ testing ----------------
-dontwarn org.mockito.**

-dontwarn java.nio.file.Files
-dontwarn java.nio.file.Path
-dontwarn java.nio.file.OpenOption
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

#----------- only for testIR module -----------
-dontnote com.google.common.reflect.**
-dontwarn com.viewpagerindicator.**