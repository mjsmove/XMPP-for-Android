.class Lorg/apache/harmony/jndi/provider/ldap/asn1/LdapASN1Constant$3;
.super Lorg/apache/harmony/jndi/provider/ldap/asn1/ASN1SequenceWrap;
.source "LdapASN1Constant.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/apache/harmony/jndi/provider/ldap/asn1/LdapASN1Constant;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# direct methods
.method constructor <init>([Lorg/apache/harmony/security/asn1/ASN1Type;)V
    .locals 2
    .parameter "$anonymous0"

    .prologue
    .line 119
    invoke-direct {p0, p1}, Lorg/apache/harmony/jndi/provider/ldap/asn1/ASN1SequenceWrap;-><init>([Lorg/apache/harmony/security/asn1/ASN1Type;)V

    .line 124
    sget-object v0, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    const/4 v1, 0x1

    invoke-virtual {p0, v0, v1}, Lorg/apache/harmony/jndi/provider/ldap/asn1/LdapASN1Constant$3;->setDefault(Ljava/lang/Object;I)V

    .line 125
    const/4 v0, 0x2

    invoke-virtual {p0, v0}, Lorg/apache/harmony/jndi/provider/ldap/asn1/LdapASN1Constant$3;->setOptional(I)V

    .line 1
    return-void
.end method
