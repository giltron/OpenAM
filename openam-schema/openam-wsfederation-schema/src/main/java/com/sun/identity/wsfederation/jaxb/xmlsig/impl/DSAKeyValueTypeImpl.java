//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-b27-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.11 at 10:34:16 AM PDT 
//


package com.sun.identity.wsfederation.jaxb.xmlsig.impl;

public class DSAKeyValueTypeImpl implements com.sun.identity.wsfederation.jaxb.xmlsig.DSAKeyValueType, com.sun.xml.bind.JAXBObject, com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.UnmarshallableObject, com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.XMLSerializable, com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.ValidatableObject
{

    protected byte[] _G;
    protected byte[] _Q;
    protected byte[] _P;
    protected byte[] _Seed;
    protected byte[] _Y;
    protected byte[] _PgenCounter;
    protected byte[] _J;
    public final static java.lang.Class version = (com.sun.identity.wsfederation.jaxb.xmlsig.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.sun.identity.wsfederation.jaxb.xmlsig.DSAKeyValueType.class);
    }

    public byte[] getG() {
        return _G;
    }

    public void setG(byte[] value) {
        _G = value;
    }

    public byte[] getQ() {
        return _Q;
    }

    public void setQ(byte[] value) {
        _Q = value;
    }

    public byte[] getP() {
        return _P;
    }

    public void setP(byte[] value) {
        _P = value;
    }

    public byte[] getSeed() {
        return _Seed;
    }

    public void setSeed(byte[] value) {
        _Seed = value;
    }

    public byte[] getY() {
        return _Y;
    }

    public void setY(byte[] value) {
        _Y = value;
    }

    public byte[] getPgenCounter() {
        return _PgenCounter;
    }

    public void setPgenCounter(byte[] value) {
        _PgenCounter = value;
    }

    public byte[] getJ() {
        return _J;
    }

    public void setJ(byte[] value) {
        _J = value;
    }

    public com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.UnmarshallingContext context) {
        return new com.sun.identity.wsfederation.jaxb.xmlsig.impl.DSAKeyValueTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if ((_Q!= null)&&(_P!= null)) {
            context.startElement("http://www.w3.org/2000/09/xmldsig#", "P");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(com.sun.msv.datatype.xsd.Base64BinaryType.save(((byte[]) _P)), "P");
            } catch (java.lang.Exception e) {
                com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
            context.startElement("http://www.w3.org/2000/09/xmldsig#", "Q");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(com.sun.msv.datatype.xsd.Base64BinaryType.save(((byte[]) _Q)), "Q");
            } catch (java.lang.Exception e) {
                com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_G!= null) {
            context.startElement("http://www.w3.org/2000/09/xmldsig#", "G");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(com.sun.msv.datatype.xsd.Base64BinaryType.save(((byte[]) _G)), "G");
            } catch (java.lang.Exception e) {
                com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        context.startElement("http://www.w3.org/2000/09/xmldsig#", "Y");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(com.sun.msv.datatype.xsd.Base64BinaryType.save(((byte[]) _Y)), "Y");
        } catch (java.lang.Exception e) {
            com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        if (_J!= null) {
            context.startElement("http://www.w3.org/2000/09/xmldsig#", "J");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(com.sun.msv.datatype.xsd.Base64BinaryType.save(((byte[]) _J)), "J");
            } catch (java.lang.Exception e) {
                com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if ((_PgenCounter!= null)&&(_Seed!= null)) {
            context.startElement("http://www.w3.org/2000/09/xmldsig#", "Seed");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(com.sun.msv.datatype.xsd.Base64BinaryType.save(((byte[]) _Seed)), "Seed");
            } catch (java.lang.Exception e) {
                com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
            context.startElement("http://www.w3.org/2000/09/xmldsig#", "PgenCounter");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(com.sun.msv.datatype.xsd.Base64BinaryType.save(((byte[]) _PgenCounter)), "PgenCounter");
            } catch (java.lang.Exception e) {
                com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
    }

    public void serializeAttributes(com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public void serializeURIs(com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.sun.identity.wsfederation.jaxb.xmlsig.DSAKeyValueType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsr\u0000\u001dcom.sun.msv."
+"grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsq\u0000~\u0000\u0000sr\u0000\u0011java.lang.Bool"
+"ean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000\'com.sun.msv.grammar.trex.Eleme"
+"ntPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/Name"
+"Class;xr\u0000\u001ecom.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignore"
+"UndeclaredAttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003q\u0000~\u0000\rp\u0000sq\u0000~\u0000\u0000p"
+"psr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/rela"
+"xng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/u"
+"til/StringPair;xq\u0000~\u0000\u0003ppsr\u0000\'com.sun.msv.datatype.xsd.FinalCom"
+"ponent\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001I\u0000\nfinalValuexr\u0000\u001ecom.sun.msv.datatype.xsd.P"
+"roxy\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bbaseTypet\u0000)Lcom/sun/msv/datatype/xsd/XSDat"
+"atypeImpl;xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String;L\u0000\btypeNameq\u0000~\u0000\u001bL\u0000\nw"
+"hiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProcessor;xp"
+"t\u0000\"http://www.w3.org/2000/09/xmldsig#t\u0000\fCryptoBinarysr\u00005com."
+"sun.msv.datatype.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000"
+"xr\u0000,com.sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000x"
+"psr\u0000)com.sun.msv.datatype.xsd.Base64BinaryType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000"
+"\'com.sun.msv.datatype.xsd.BinaryBaseType\u00a7\u00ce\u000e\u0097^\u00afW\u0011\u0002\u0000\u0000xr\u0000*com.s"
+"un.msv.datatype.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun."
+"msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u001at\u0000 http://www."
+"w3.org/2001/XMLSchemat\u0000\fbase64Binaryq\u0000~\u0000\"\u0000\u0000\u0000\u0000sr\u00000com.sun.msv"
+".grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003q\u0000~\u0000\rp"
+"sr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001bL"
+"\u0000\fnamespaceURIq\u0000~\u0000\u001bxpq\u0000~\u0000)q\u0000~\u0000\u001esq\u0000~\u0000\tppsr\u0000 com.sun.msv.gramm"
+"ar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\u000fxq\u0000~\u0000\u0003"
+"q\u0000~\u0000\rpsq\u0000~\u0000\u0013ppsr\u0000\"com.sun.msv.datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001"
+"\u0002\u0000\u0000xq\u0000~\u0000%q\u0000~\u0000(t\u0000\u0005QNameq\u0000~\u0000\"q\u0000~\u0000+sq\u0000~\u0000,q\u0000~\u00004q\u0000~\u0000(sr\u0000#com.sun."
+"msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001bL\u0000\fna"
+"mespaceURIq\u0000~\u0000\u001bxr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000x"
+"pt\u0000\u0004typet\u0000)http://www.w3.org/2001/XMLSchema-instancesr\u00000com."
+"sun.msv.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000"
+"\u0003sq\u0000~\u0000\f\u0001q\u0000~\u0000<sq\u0000~\u00006t\u0000\u0001Pq\u0000~\u0000\u001esq\u0000~\u0000\u000epp\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000\u0016sq\u0000~\u0000\tppsq"
+"\u0000~\u0000/q\u0000~\u0000\rpq\u0000~\u00001q\u0000~\u00008q\u0000~\u0000<sq\u0000~\u00006t\u0000\u0001Qq\u0000~\u0000\u001eq\u0000~\u0000<sq\u0000~\u0000\tppsq\u0000~\u0000\u000eq"
+"\u0000~\u0000\rp\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000\u0016sq\u0000~\u0000\tppsq\u0000~\u0000/q\u0000~\u0000\rpq\u0000~\u00001q\u0000~\u00008q\u0000~\u0000<sq\u0000~\u00006"
+"t\u0000\u0001Gq\u0000~\u0000\u001eq\u0000~\u0000<sq\u0000~\u0000\u000epp\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000\u0016sq\u0000~\u0000\tppsq\u0000~\u0000/q\u0000~\u0000\rpq\u0000~\u0000"
+"1q\u0000~\u00008q\u0000~\u0000<sq\u0000~\u00006t\u0000\u0001Yq\u0000~\u0000\u001esq\u0000~\u0000\tppsq\u0000~\u0000\u000eq\u0000~\u0000\rp\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000\u0016"
+"sq\u0000~\u0000\tppsq\u0000~\u0000/q\u0000~\u0000\rpq\u0000~\u00001q\u0000~\u00008q\u0000~\u0000<sq\u0000~\u00006t\u0000\u0001Jq\u0000~\u0000\u001eq\u0000~\u0000<sq\u0000~\u0000"
+"\tppsq\u0000~\u0000\u0000q\u0000~\u0000\rpsq\u0000~\u0000\u000eq\u0000~\u0000\rp\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000\u0016sq\u0000~\u0000\tppsq\u0000~\u0000/q\u0000~\u0000\r"
+"pq\u0000~\u00001q\u0000~\u00008q\u0000~\u0000<sq\u0000~\u00006t\u0000\u0004Seedq\u0000~\u0000\u001esq\u0000~\u0000\u000epp\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000\u0016sq\u0000~"
+"\u0000\tppsq\u0000~\u0000/q\u0000~\u0000\rpq\u0000~\u00001q\u0000~\u00008q\u0000~\u0000<sq\u0000~\u00006t\u0000\u000bPgenCounterq\u0000~\u0000\u001eq\u0000~\u0000"
+"<sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTabl"
+"et\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedHash;xpsr\u0000-com"
+".sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005coun"
+"tB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/grammar/Expression"
+"Pool;xp\u0000\u0000\u0000\u0018\u0001pq\u0000~\u0000\bq\u0000~\u0000\u0012q\u0000~\u0000Aq\u0000~\u0000\nq\u0000~\u0000Hq\u0000~\u0000Nq\u0000~\u0000Uq\u0000~\u0000]q\u0000~\u0000cq\u0000"
+"~\u0000Zq\u0000~\u0000.q\u0000~\u0000Bq\u0000~\u0000Iq\u0000~\u0000Oq\u0000~\u0000Vq\u0000~\u0000^q\u0000~\u0000dq\u0000~\u0000\u0005q\u0000~\u0000\u0007q\u0000~\u0000\u0006q\u0000~\u0000\u000bq\u0000"
+"~\u0000[q\u0000~\u0000Fq\u0000~\u0000Sx"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.UnmarshallingContext context) {
            super(context, "----------------------");
        }

        protected Unmarshaller(com.sun.identity.wsfederation.jaxb.entityconfig.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.sun.identity.wsfederation.jaxb.xmlsig.impl.DSAKeyValueTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  15 :
                        if (("Seed" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 16;
                            return ;
                        }
                        state = 21;
                        continue outer;
                    case  21 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  3 :
                        if (("Q" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 4;
                            return ;
                        }
                        break;
                    case  18 :
                        if (("PgenCounter" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 19;
                            return ;
                        }
                        break;
                    case  6 :
                        if (("G" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 7;
                            return ;
                        }
                        state = 9;
                        continue outer;
                    case  9 :
                        if (("Y" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 10;
                            return ;
                        }
                        break;
                    case  0 :
                        if (("P" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 1;
                            return ;
                        }
                        state = 6;
                        continue outer;
                    case  12 :
                        if (("J" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 13;
                            return ;
                        }
                        state = 15;
                        continue outer;
                }
                super.enterElement(___uri, ___local, ___qname, __atts);
                break;
            }
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  15 :
                        state = 21;
                        continue outer;
                    case  14 :
                        if (("J" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.popAttributes();
                            state = 15;
                            return ;
                        }
                        break;
                    case  21 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  17 :
                        if (("Seed" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.popAttributes();
                            state = 18;
                            return ;
                        }
                        break;
                    case  11 :
                        if (("Y" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.popAttributes();
                            state = 12;
                            return ;
                        }
                        break;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  20 :
                        if (("PgenCounter" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.popAttributes();
                            state = 21;
                            return ;
                        }
                        break;
                    case  2 :
                        if (("P" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  8 :
                        if (("G" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.popAttributes();
                            state = 9;
                            return ;
                        }
                        break;
                    case  0 :
                        state = 6;
                        continue outer;
                    case  5 :
                        if (("Q" == ___local)&&("http://www.w3.org/2000/09/xmldsig#" == ___uri)) {
                            context.popAttributes();
                            state = 6;
                            return ;
                        }
                        break;
                    case  12 :
                        state = 15;
                        continue outer;
                }
                super.leaveElement(___uri, ___local, ___qname);
                break;
            }
        }

        public void enterAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  15 :
                        state = 21;
                        continue outer;
                    case  21 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  0 :
                        state = 6;
                        continue outer;
                    case  12 :
                        state = 15;
                        continue outer;
                }
                super.enterAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  15 :
                        state = 21;
                        continue outer;
                    case  21 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  0 :
                        state = 6;
                        continue outer;
                    case  12 :
                        state = 15;
                        continue outer;
                }
                super.leaveAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void handleText(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                try {
                    switch (state) {
                        case  15 :
                            state = 21;
                            continue outer;
                        case  1 :
                            state = 2;
                            eatText1(value);
                            return ;
                        case  13 :
                            state = 14;
                            eatText2(value);
                            return ;
                        case  7 :
                            state = 8;
                            eatText3(value);
                            return ;
                        case  21 :
                            revertToParentFromText(value);
                            return ;
                        case  19 :
                            state = 20;
                            eatText4(value);
                            return ;
                        case  4 :
                            state = 5;
                            eatText5(value);
                            return ;
                        case  6 :
                            state = 9;
                            continue outer;
                        case  16 :
                            state = 17;
                            eatText6(value);
                            return ;
                        case  10 :
                            state = 11;
                            eatText7(value);
                            return ;
                        case  0 :
                            state = 6;
                            continue outer;
                        case  12 :
                            state = 15;
                            continue outer;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

        private void eatText1(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _P = com.sun.msv.datatype.xsd.Base64BinaryType.load(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _J = com.sun.msv.datatype.xsd.Base64BinaryType.load(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText3(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _G = com.sun.msv.datatype.xsd.Base64BinaryType.load(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText4(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _PgenCounter = com.sun.msv.datatype.xsd.Base64BinaryType.load(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText5(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Q = com.sun.msv.datatype.xsd.Base64BinaryType.load(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText6(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Seed = com.sun.msv.datatype.xsd.Base64BinaryType.load(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText7(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Y = com.sun.msv.datatype.xsd.Base64BinaryType.load(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}
