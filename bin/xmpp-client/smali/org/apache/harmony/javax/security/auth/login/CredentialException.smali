.class public Lorg/apache/harmony/javax/security/auth/login/CredentialException;
.super Ljavax/security/auth/login/LoginException;
.source "CredentialException.java"


# static fields
.field private static final serialVersionUID:J = -0x423cb9b16deed583L


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 27
    invoke-direct {p0}, Ljavax/security/auth/login/LoginException;-><init>()V

    .line 28
    return-void
.end method

.method public constructor <init>(Ljava/lang/String;)V
    .locals 0
    .parameter "message"

    .prologue
    .line 31
    invoke-direct {p0, p1}, Ljavax/security/auth/login/LoginException;-><init>(Ljava/lang/String;)V

    .line 32
    return-void
.end method
