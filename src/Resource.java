import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/**
 * Resource (file or directory) representation class. 
 *
 */
public class Resource implements Cloneable {
	private static HashMap<String, String> mimeTypes;
	
	private static String DEFAULT_MIME_TYPE = "application/octet-stream";
	
	static {
		mimeTypes = new HashMap<String, String>();
		mimeTypes.put("ez", "application/andrew-inset");
		mimeTypes.put("aw", "application/applixware");
		mimeTypes.put("atom", "application/atom+xml");
		mimeTypes.put("atomcat", "application/atomcat+xml");
		mimeTypes.put("atomsvc", "application/atomsvc+xml");
		mimeTypes.put("ccxml", "application/ccxml+xml");
		mimeTypes.put("cu", "application/cu-seeme");
		mimeTypes.put("davmount", "application/davmount+xml");
		mimeTypes.put("dssc", "application/dssc+der");
		mimeTypes.put("xdssc", "application/dssc+xml");
		mimeTypes.put("ecma", "application/ecmascript");
		mimeTypes.put("emma", "application/emma+xml");
		mimeTypes.put("epub", "application/epub+zip");
		mimeTypes.put("pfr", "application/font-tdpfr");
		mimeTypes.put("stk", "application/hyperstudio");
		mimeTypes.put("ipfix", "application/ipfix");
		mimeTypes.put("jar", "application/java-archive");
		mimeTypes.put("ser", "application/java-serialized-object");
		mimeTypes.put("class", "application/java-vm");
		mimeTypes.put("js", "application/javascript");
		mimeTypes.put("json", "application/json");
		mimeTypes.put("lostxml", "application/lost+xml");
		mimeTypes.put("hqx", "application/mac-binhex40");
		mimeTypes.put("cpt", "application/mac-compactpro");
		mimeTypes.put("mrc", "application/marc");
		mimeTypes.put("ma", "application/mathematica");
		mimeTypes.put("nb", "application/mathematica");
		mimeTypes.put("mb", "application/mathematica");
		mimeTypes.put("mathml", "application/mathml+xml");
		mimeTypes.put("mbox", "application/mbox");
		mimeTypes.put("mscml", "application/mediaservercontrol+xml");
		mimeTypes.put("mp4s", "application/mp4");
		mimeTypes.put("doc", "application/msword");
		mimeTypes.put("dot", "application/msword");
		mimeTypes.put("mxf", "application/mxf");
		mimeTypes.put("bin", "application/octet-stream");
		mimeTypes.put("dms", "application/octet-stream");
		mimeTypes.put("lha", "application/octet-stream");
		mimeTypes.put("lrf", "application/octet-stream");
		mimeTypes.put("lzh", "application/octet-stream");
		mimeTypes.put("so", "application/octet-stream");
		mimeTypes.put("iso", "application/octet-stream");
		mimeTypes.put("dmg", "application/octet-stream");
		mimeTypes.put("dist", "application/octet-stream");
		mimeTypes.put("distz", "application/octet-stream");
		mimeTypes.put("oda", "application/oda");
		mimeTypes.put("opf", "application/oebps-package+xml");
		mimeTypes.put("ogx", "application/ogg");
		mimeTypes.put("onetoc", "application/onenote");
		mimeTypes.put("onetoc2", "application/onenote");
		mimeTypes.put("onetmp", "application/onenote");
		mimeTypes.put("onepkg", "application/onenote");
		mimeTypes.put("xer", "application/patch-ops-error+xml");
		mimeTypes.put("pdf", "application/pdf");
		mimeTypes.put("pgp", "application/pgp-encrypted");
		mimeTypes.put("asc", "application/pgp-signature");
		mimeTypes.put("sig", "application/pgp-signature");
		mimeTypes.put("prf", "application/pics-rules");
		mimeTypes.put("p10", "application/pkcs10");
		mimeTypes.put("p7m", "application/pkcs7-mime");
		mimeTypes.put("p7c", "application/pkcs7-mime");
		mimeTypes.put("p7s", "application/pkcs7-signature");
		mimeTypes.put("cer", "application/pkix-cert");
		mimeTypes.put("crl", "application/pkix-crl");
		mimeTypes.put("pkipath", "application/pkix-pkipath");
		mimeTypes.put("pki", "application/pkixcmp");
		mimeTypes.put("pls", "application/pls+xml");
		mimeTypes.put("ai", "application/postscript");
		mimeTypes.put("eps", "application/postscript");
		mimeTypes.put("ps", "application/postscript");
		mimeTypes.put("cww", "application/prs.cww");
		mimeTypes.put("rdf", "application/rdf+xml");
		mimeTypes.put("rif", "application/reginfo+xml");
		mimeTypes.put("rnc", "application/relax-ng-compact-syntax");
		mimeTypes.put("rl", "application/resource-lists+xml");
		mimeTypes.put("rld", "application/resource-lists-diff+xml");
		mimeTypes.put("rs", "application/rls-services+xml");
		mimeTypes.put("rsd", "application/rsd+xml");
		mimeTypes.put("rss", "application/rss+xml");
		mimeTypes.put("rtf", "application/rtf");
		mimeTypes.put("sbml", "application/sbml+xml");
		mimeTypes.put("scq", "application/scvp-cv-request");
		mimeTypes.put("scs", "application/scvp-cv-response");
		mimeTypes.put("spq", "application/scvp-vp-request");
		mimeTypes.put("spp", "application/scvp-vp-response");
		mimeTypes.put("sdp", "application/sdp");
		mimeTypes.put("setpay", "application/set-payment-initiation");
		mimeTypes.put("setreg", "application/set-registration-initiation");
		mimeTypes.put("shf", "application/shf+xml");
		mimeTypes.put("smi", "application/smil+xml");
		mimeTypes.put("smil", "application/smil+xml");
		mimeTypes.put("rq", "application/sparql-query");
		mimeTypes.put("srx", "application/sparql-results+xml");
		mimeTypes.put("gram", "application/srgs");
		mimeTypes.put("grxml", "application/srgs+xml");
		mimeTypes.put("ssml", "application/ssml+xml");
		mimeTypes.put("plb", "application/vnd.3gpp.pic-bw-large");
		mimeTypes.put("psb", "application/vnd.3gpp.pic-bw-small");
		mimeTypes.put("pvb", "application/vnd.3gpp.pic-bw-var");
		mimeTypes.put("tcap", "application/vnd.3gpp2.tcap");
		mimeTypes.put("pwn", "application/vnd.3m.post-it-notes");
		mimeTypes.put("aso", "application/vnd.accpac.simply.aso");
		mimeTypes.put("imp", "application/vnd.accpac.simply.imp");
		mimeTypes.put("acu", "application/vnd.acucobol");
		mimeTypes.put("atc", "application/vnd.acucorp");
		mimeTypes.put("acutc", "application/vnd.acucorp");
		mimeTypes.put("air", "application/vnd.adobe.air-application-installer-package+zip");
		mimeTypes.put("xdp", "application/vnd.adobe.xdp+xml");
		mimeTypes.put("xfdf", "application/vnd.adobe.xfdf");
		mimeTypes.put("azf", "application/vnd.airzip.filesecure.azf");
		mimeTypes.put("azs", "application/vnd.airzip.filesecure.azs");
		mimeTypes.put("azw", "application/vnd.amazon.ebook");
		mimeTypes.put("acc", "application/vnd.americandynamics.acc");
		mimeTypes.put("ami", "application/vnd.amiga.ami");
		mimeTypes.put("apk", "application/vnd.android.package-archive");
		mimeTypes.put("cii", "application/vnd.anser-web-certificate-issue-initiation");
		mimeTypes.put("fti", "application/vnd.anser-web-funds-transfer-initiation");
		mimeTypes.put("atx", "application/vnd.antix.game-component");
		mimeTypes.put("mpkg", "application/vnd.apple.installer+xml");
		mimeTypes.put("m3u8", "application/vnd.apple.mpegurl");
		mimeTypes.put("swi", "application/vnd.aristanetworks.swi");
		mimeTypes.put("aep", "application/vnd.audiograph");
		mimeTypes.put("mpm", "application/vnd.blueice.multipass");
		mimeTypes.put("bmi", "application/vnd.bmi");
		mimeTypes.put("rep", "application/vnd.businessobjects");
		mimeTypes.put("cdxml", "application/vnd.chemdraw+xml");
		mimeTypes.put("mmd", "application/vnd.chipnuts.karaoke-mmd");
		mimeTypes.put("cdy", "application/vnd.cinderella");
		mimeTypes.put("cla", "application/vnd.claymore");
		mimeTypes.put("rp9", "application/vnd.cloanto.rp9");
		mimeTypes.put("c4g", "application/vnd.clonk.c4group");
		mimeTypes.put("c4d", "application/vnd.clonk.c4group");
		mimeTypes.put("c4f", "application/vnd.clonk.c4group");
		mimeTypes.put("c4p", "application/vnd.clonk.c4group");
		mimeTypes.put("c4u", "application/vnd.clonk.c4group");
		mimeTypes.put("csp", "application/vnd.commonspace");
		mimeTypes.put("cdbcmsg", "application/vnd.contact.cmsg");
		mimeTypes.put("cmc", "application/vnd.cosmocaller");
		mimeTypes.put("clkx", "application/vnd.crick.clicker");
		mimeTypes.put("clkk", "application/vnd.crick.clicker.keyboard");
		mimeTypes.put("clkp", "application/vnd.crick.clicker.palette");
		mimeTypes.put("clkt", "application/vnd.crick.clicker.template");
		mimeTypes.put("clkw", "application/vnd.crick.clicker.wordbank");
		mimeTypes.put("wbs", "application/vnd.criticaltools.wbs+xml");
		mimeTypes.put("pml", "application/vnd.ctc-posml");
		mimeTypes.put("ppd", "application/vnd.cups-ppd");
		mimeTypes.put("car", "application/vnd.curl.car");
		mimeTypes.put("pcurl", "application/vnd.curl.pcurl");
		mimeTypes.put("rdz", "application/vnd.data-vision.rdz");
		mimeTypes.put("fe_launch", "application/vnd.denovo.fcselayout-link");
		mimeTypes.put("dna", "application/vnd.dna");
		mimeTypes.put("mlp", "application/vnd.dolby.mlp");
		mimeTypes.put("dpg", "application/vnd.dpgraph");
		mimeTypes.put("dfac", "application/vnd.dreamfactory");
		mimeTypes.put("geo", "application/vnd.dynageo");
		mimeTypes.put("mag", "application/vnd.ecowin.chart");
		mimeTypes.put("nml", "application/vnd.enliven");
		mimeTypes.put("esf", "application/vnd.epson.esf");
		mimeTypes.put("msf", "application/vnd.epson.msf");
		mimeTypes.put("qam", "application/vnd.epson.quickanime");
		mimeTypes.put("slt", "application/vnd.epson.salt");
		mimeTypes.put("ssf", "application/vnd.epson.ssf");
		mimeTypes.put("es3", "application/vnd.eszigno3+xml");
		mimeTypes.put("et3", "application/vnd.eszigno3+xml");
		mimeTypes.put("ez2", "application/vnd.ezpix-album");
		mimeTypes.put("ez3", "application/vnd.ezpix-package");
		mimeTypes.put("fdf", "application/vnd.fdf");
		mimeTypes.put("mseed", "application/vnd.fdsn.mseed");
		mimeTypes.put("seed", "application/vnd.fdsn.seed");
		mimeTypes.put("dataless", "application/vnd.fdsn.seed");
		mimeTypes.put("gph", "application/vnd.flographit");
		mimeTypes.put("ftc", "application/vnd.fluxtime.clip");
		mimeTypes.put("fm", "application/vnd.framemaker");
		mimeTypes.put("frame", "application/vnd.framemaker");
		mimeTypes.put("maker", "application/vnd.framemaker");
		mimeTypes.put("book", "application/vnd.framemaker");
		mimeTypes.put("fnc", "application/vnd.frogans.fnc");
		mimeTypes.put("ltf", "application/vnd.frogans.ltf");
		mimeTypes.put("fsc", "application/vnd.fsc.weblaunch");
		mimeTypes.put("oas", "application/vnd.fujitsu.oasys");
		mimeTypes.put("oa2", "application/vnd.fujitsu.oasys2");
		mimeTypes.put("oa3", "application/vnd.fujitsu.oasys3");
		mimeTypes.put("fg5", "application/vnd.fujitsu.oasysgp");
		mimeTypes.put("bh2", "application/vnd.fujitsu.oasysprs");
		mimeTypes.put("ddd", "application/vnd.fujixerox.ddd");
		mimeTypes.put("xdw", "application/vnd.fujixerox.docuworks");
		mimeTypes.put("xbd", "application/vnd.fujixerox.docuworks.binder");
		mimeTypes.put("fzs", "application/vnd.fuzzysheet");
		mimeTypes.put("txd", "application/vnd.genomatix.tuxedo");
		mimeTypes.put("ggb", "application/vnd.geogebra.file");
		mimeTypes.put("ggt", "application/vnd.geogebra.tool");
		mimeTypes.put("gex", "application/vnd.geometry-explorer");
		mimeTypes.put("gre", "application/vnd.geometry-explorer");
		mimeTypes.put("gxt", "application/vnd.geonext");
		mimeTypes.put("g2w", "application/vnd.geoplan");
		mimeTypes.put("g3w", "application/vnd.geospace");
		mimeTypes.put("gmx", "application/vnd.gmx");
		mimeTypes.put("kml", "application/vnd.google-earth.kml+xml");
		mimeTypes.put("kmz", "application/vnd.google-earth.kmz");
		mimeTypes.put("gqf", "application/vnd.grafeq");
		mimeTypes.put("gqs", "application/vnd.grafeq");
		mimeTypes.put("gac", "application/vnd.groove-account");
		mimeTypes.put("ghf", "application/vnd.groove-help");
		mimeTypes.put("gim", "application/vnd.groove-identity-message");
		mimeTypes.put("grv", "application/vnd.groove-injector");
		mimeTypes.put("gtm", "application/vnd.groove-tool-message");
		mimeTypes.put("tpl", "application/vnd.groove-tool-template");
		mimeTypes.put("vcg", "application/vnd.groove-vcard");
		mimeTypes.put("zmm", "application/vnd.handheld-entertainment+xml");
		mimeTypes.put("hbci", "application/vnd.hbci");
		mimeTypes.put("les", "application/vnd.hhe.lesson-player");
		mimeTypes.put("hpgl", "application/vnd.hp-hpgl");
		mimeTypes.put("hpid", "application/vnd.hp-hpid");
		mimeTypes.put("hps", "application/vnd.hp-hps");
		mimeTypes.put("jlt", "application/vnd.hp-jlyt");
		mimeTypes.put("pcl", "application/vnd.hp-pcl");
		mimeTypes.put("pclxl", "application/vnd.hp-pclxl");
		mimeTypes.put("sfd-hdstx", "application/vnd.hydrostatix.sof-data");
		mimeTypes.put("x3d", "application/vnd.hzn-3d-crossword");
		mimeTypes.put("mpy", "application/vnd.ibm.minipay");
		mimeTypes.put("afp", "application/vnd.ibm.modcap");
		mimeTypes.put("listafp", "application/vnd.ibm.modcap");
		mimeTypes.put("list3820", "application/vnd.ibm.modcap");
		mimeTypes.put("irm", "application/vnd.ibm.rights-management");
		mimeTypes.put("sc", "application/vnd.ibm.secure-container");
		mimeTypes.put("icc", "application/vnd.iccprofile");
		mimeTypes.put("icm", "application/vnd.iccprofile");
		mimeTypes.put("igl", "application/vnd.igloader");
		mimeTypes.put("ivp", "application/vnd.immervision-ivp");
		mimeTypes.put("ivu", "application/vnd.immervision-ivu");
		mimeTypes.put("xpw", "application/vnd.intercon.formnet");
		mimeTypes.put("xpx", "application/vnd.intercon.formnet");
		mimeTypes.put("qbo", "application/vnd.intu.qbo");
		mimeTypes.put("qfx", "application/vnd.intu.qfx");
		mimeTypes.put("rcprofile", "application/vnd.ipunplugged.rcprofile");
		mimeTypes.put("irp", "application/vnd.irepository.package+xml");
		mimeTypes.put("xpr", "application/vnd.is-xpr");
		mimeTypes.put("jam", "application/vnd.jam");
		mimeTypes.put("rms", "application/vnd.jcp.javame.midlet-rms");
		mimeTypes.put("jisp", "application/vnd.jisp");
		mimeTypes.put("joda", "application/vnd.joost.joda-archive");
		mimeTypes.put("ktz", "application/vnd.kahootz");
		mimeTypes.put("ktr", "application/vnd.kahootz");
		mimeTypes.put("karbon", "application/vnd.kde.karbon");
		mimeTypes.put("chrt", "application/vnd.kde.kchart");
		mimeTypes.put("kfo", "application/vnd.kde.kformula");
		mimeTypes.put("flw", "application/vnd.kde.kivio");
		mimeTypes.put("kon", "application/vnd.kde.kontour");
		mimeTypes.put("kpr", "application/vnd.kde.kpresenter");
		mimeTypes.put("kpt", "application/vnd.kde.kpresenter");
		mimeTypes.put("ksp", "application/vnd.kde.kspread");
		mimeTypes.put("kwd", "application/vnd.kde.kword");
		mimeTypes.put("kwt", "application/vnd.kde.kword");
		mimeTypes.put("htke", "application/vnd.kenameaapp");
		mimeTypes.put("kia", "application/vnd.kidspiration");
		mimeTypes.put("kne", "application/vnd.kinar");
		mimeTypes.put("knp", "application/vnd.kinar");
		mimeTypes.put("skp", "application/vnd.koan");
		mimeTypes.put("skd", "application/vnd.koan");
		mimeTypes.put("skt", "application/vnd.koan");
		mimeTypes.put("skm", "application/vnd.koan");
		mimeTypes.put("sse", "application/vnd.kodak-descriptor");
		mimeTypes.put("lbd", "application/vnd.llamagraphics.life-balance.desktop");
		mimeTypes.put("lbe", "application/vnd.llamagraphics.life-balance.exchange+xml");
		mimeTypes.put("123", "application/vnd.lotus-1-2-3");
		mimeTypes.put("apr", "application/vnd.lotus-approach");
		mimeTypes.put("pre", "application/vnd.lotus-freelance");
		mimeTypes.put("nsf", "application/vnd.lotus-notes");
		mimeTypes.put("org", "application/vnd.lotus-organizer");
		mimeTypes.put("scm", "application/vnd.lotus-screencam");
		mimeTypes.put("lwp", "application/vnd.lotus-wordpro");
		mimeTypes.put("portpkg", "application/vnd.macports.portpkg");
		mimeTypes.put("mcd", "application/vnd.mcd");
		mimeTypes.put("mc1", "application/vnd.medcalcdata");
		mimeTypes.put("cdkey", "application/vnd.mediastation.cdkey");
		mimeTypes.put("mwf", "application/vnd.mfer");
		mimeTypes.put("mfm", "application/vnd.mfmp");
		mimeTypes.put("flo", "application/vnd.micrografx.flo");
		mimeTypes.put("igx", "application/vnd.micrografx.igx");
		mimeTypes.put("mif", "application/vnd.mif");
		mimeTypes.put("daf", "application/vnd.mobius.daf");
		mimeTypes.put("dis", "application/vnd.mobius.dis");
		mimeTypes.put("mbk", "application/vnd.mobius.mbk");
		mimeTypes.put("mqy", "application/vnd.mobius.mqy");
		mimeTypes.put("msl", "application/vnd.mobius.msl");
		mimeTypes.put("plc", "application/vnd.mobius.plc");
		mimeTypes.put("txf", "application/vnd.mobius.txf");
		mimeTypes.put("mpn", "application/vnd.mophun.application");
		mimeTypes.put("mpc", "application/vnd.mophun.certificate");
		mimeTypes.put("xul", "application/vnd.mozilla.xul+xml");
		mimeTypes.put("cil", "application/vnd.ms-artgalry");
		mimeTypes.put("cab", "application/vnd.ms-cab-compressed");
		mimeTypes.put("xls", "application/vnd.ms-excel");
		mimeTypes.put("xlm", "application/vnd.ms-excel");
		mimeTypes.put("xla", "application/vnd.ms-excel");
		mimeTypes.put("xlc", "application/vnd.ms-excel");
		mimeTypes.put("xlt", "application/vnd.ms-excel");
		mimeTypes.put("xlw", "application/vnd.ms-excel");
		mimeTypes.put("xlam", "application/vnd.ms-excel.addin.macroenabled.12");
		mimeTypes.put("xlsb", "application/vnd.ms-excel.sheet.binary.macroenabled.12");
		mimeTypes.put("xlsm", "application/vnd.ms-excel.sheet.macroenabled.12");
		mimeTypes.put("xltm", "application/vnd.ms-excel.template.macroenabled.12");
		mimeTypes.put("eot", "application/vnd.ms-fontobject");
		mimeTypes.put("chm", "application/vnd.ms-htmlhelp");
		mimeTypes.put("ims", "application/vnd.ms-ims");
		mimeTypes.put("lrm", "application/vnd.ms-lrm");
		mimeTypes.put("cat", "application/vnd.ms-pki.seccat");
		mimeTypes.put("stl", "application/vnd.ms-pki.stl");
		mimeTypes.put("ppt", "application/vnd.ms-powerpoint");
		mimeTypes.put("pps", "application/vnd.ms-powerpoint");
		mimeTypes.put("pot", "application/vnd.ms-powerpoint");
		mimeTypes.put("ppam", "application/vnd.ms-powerpoint.addin.macroenabled.12");
		mimeTypes.put("pptm", "application/vnd.ms-powerpoint.presentation.macroenabled.12");
		mimeTypes.put("sldm", "application/vnd.ms-powerpoint.slide.macroenabled.12");
		mimeTypes.put("ppsm", "application/vnd.ms-powerpoint.slideshow.macroenabled.12");
		mimeTypes.put("potm", "application/vnd.ms-powerpoint.template.macroenabled.12");
		mimeTypes.put("mpp", "application/vnd.ms-project");
		mimeTypes.put("mpt", "application/vnd.ms-project");
		mimeTypes.put("docm", "application/vnd.ms-word.document.macroenabled.12");
		mimeTypes.put("dotm", "application/vnd.ms-word.template.macroenabled.12");
		mimeTypes.put("wps", "application/vnd.ms-works");
		mimeTypes.put("wks", "application/vnd.ms-works");
		mimeTypes.put("wcm", "application/vnd.ms-works");
		mimeTypes.put("wdb", "application/vnd.ms-works");
		mimeTypes.put("wpl", "application/vnd.ms-wpl");
		mimeTypes.put("xps", "application/vnd.ms-xpsdocument");
		mimeTypes.put("mseq", "application/vnd.mseq");
		mimeTypes.put("mus", "application/vnd.musician");
		mimeTypes.put("msty", "application/vnd.muvee.style");
		mimeTypes.put("nlu", "application/vnd.neurolanguage.nlu");
		mimeTypes.put("nnd", "application/vnd.noblenet-directory");
		mimeTypes.put("nns", "application/vnd.noblenet-sealer");
		mimeTypes.put("nnw", "application/vnd.noblenet-web");
		mimeTypes.put("ngdat", "application/vnd.nokia.n-gage.data");
		mimeTypes.put("n-gage", "application/vnd.nokia.n-gage.symbian.install");
		mimeTypes.put("rpst", "application/vnd.nokia.radio-preset");
		mimeTypes.put("rpss", "application/vnd.nokia.radio-presets");
		mimeTypes.put("edm", "application/vnd.novadigm.edm");
		mimeTypes.put("edx", "application/vnd.novadigm.edx");
		mimeTypes.put("ext", "application/vnd.novadigm.ext");
		mimeTypes.put("odc", "application/vnd.oasis.opendocument.chart");
		mimeTypes.put("otc", "application/vnd.oasis.opendocument.chart-template");
		mimeTypes.put("odb", "application/vnd.oasis.opendocument.database");
		mimeTypes.put("odf", "application/vnd.oasis.opendocument.formula");
		mimeTypes.put("odft", "application/vnd.oasis.opendocument.formula-template");
		mimeTypes.put("odg", "application/vnd.oasis.opendocument.graphics");
		mimeTypes.put("otg", "application/vnd.oasis.opendocument.graphics-template");
		mimeTypes.put("odi", "application/vnd.oasis.opendocument.image");
		mimeTypes.put("oti", "application/vnd.oasis.opendocument.image-template");
		mimeTypes.put("odp", "application/vnd.oasis.opendocument.presentation");
		mimeTypes.put("otp", "application/vnd.oasis.opendocument.presentation-template");
		mimeTypes.put("ods", "application/vnd.oasis.opendocument.spreadsheet");
		mimeTypes.put("ots", "application/vnd.oasis.opendocument.spreadsheet-template");
		mimeTypes.put("odt", "application/vnd.oasis.opendocument.text");
		mimeTypes.put("otm", "application/vnd.oasis.opendocument.text-master");
		mimeTypes.put("ott", "application/vnd.oasis.opendocument.text-template");
		mimeTypes.put("oth", "application/vnd.oasis.opendocument.text-web");
		mimeTypes.put("xo", "application/vnd.olpc-sugar");
		mimeTypes.put("dd2", "application/vnd.oma.dd2+xml");
		mimeTypes.put("oxt", "application/vnd.openofficeorg.extension");
		mimeTypes.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
		mimeTypes.put("sldx", "application/vnd.openxmlformats-officedocument.presentationml.slide");
		mimeTypes.put("ppsx", "application/vnd.openxmlformats-officedocument.presentationml.slideshow");
		mimeTypes.put("potx", "application/vnd.openxmlformats-officedocument.presentationml.template");
		mimeTypes.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		mimeTypes.put("xltx", "application/vnd.openxmlformats-officedocument.spreadsheetml.template");
		mimeTypes.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		mimeTypes.put("dotx", "application/vnd.openxmlformats-officedocument.wordprocessingml.template");
		mimeTypes.put("dp", "application/vnd.osgi.dp");
		mimeTypes.put("pdb", "application/vnd.palm");
		mimeTypes.put("pqa", "application/vnd.palm");
		mimeTypes.put("oprc", "application/vnd.palm");
		mimeTypes.put("paw", "application/vnd.pawaafile");
		mimeTypes.put("str", "application/vnd.pg.format");
		mimeTypes.put("ei6", "application/vnd.pg.osasli");
		mimeTypes.put("efif", "application/vnd.picsel");
		mimeTypes.put("wg", "application/vnd.pmi.widget");
		mimeTypes.put("plf", "application/vnd.pocketlearn");
		mimeTypes.put("pbd", "application/vnd.powerbuilder6");
		mimeTypes.put("box", "application/vnd.previewsystems.box");
		mimeTypes.put("mgz", "application/vnd.proteus.magazine");
		mimeTypes.put("qps", "application/vnd.publishare-delta-tree");
		mimeTypes.put("ptid", "application/vnd.pvi.ptid1");
		mimeTypes.put("qxd", "application/vnd.quark.quarkxpress");
		mimeTypes.put("qxt", "application/vnd.quark.quarkxpress");
		mimeTypes.put("qwd", "application/vnd.quark.quarkxpress");
		mimeTypes.put("qwt", "application/vnd.quark.quarkxpress");
		mimeTypes.put("qxl", "application/vnd.quark.quarkxpress");
		mimeTypes.put("qxb", "application/vnd.quark.quarkxpress");
		mimeTypes.put("bed", "application/vnd.realvnc.bed");
		mimeTypes.put("mxl", "application/vnd.recordare.musicxml");
		mimeTypes.put("musicxml", "application/vnd.recordare.musicxml+xml");
		mimeTypes.put("cod", "application/vnd.rim.cod");
		mimeTypes.put("rm", "application/vnd.rn-realmedia");
		mimeTypes.put("link66", "application/vnd.route66.link66+xml");
		mimeTypes.put("st", "application/vnd.sailingtracker.track");
		mimeTypes.put("see", "application/vnd.seemail");
		mimeTypes.put("sema", "application/vnd.sema");
		mimeTypes.put("semd", "application/vnd.semd");
		mimeTypes.put("semf", "application/vnd.semf");
		mimeTypes.put("ifm", "application/vnd.shana.informed.formdata");
		mimeTypes.put("itp", "application/vnd.shana.informed.formtemplate");
		mimeTypes.put("iif", "application/vnd.shana.informed.interchange");
		mimeTypes.put("ipk", "application/vnd.shana.informed.package");
		mimeTypes.put("twd", "application/vnd.simtech-mindmapper");
		mimeTypes.put("twds", "application/vnd.simtech-mindmapper");
		mimeTypes.put("mmf", "application/vnd.smaf");
		mimeTypes.put("teacher", "application/vnd.smart.teacher");
		mimeTypes.put("sdkm", "application/vnd.solent.sdkm+xml");
		mimeTypes.put("sdkd", "application/vnd.solent.sdkm+xml");
		mimeTypes.put("dxp", "application/vnd.spotfire.dxp");
		mimeTypes.put("sfs", "application/vnd.spotfire.sfs");
		mimeTypes.put("sdc", "application/vnd.stardivision.calc");
		mimeTypes.put("sda", "application/vnd.stardivision.draw");
		mimeTypes.put("sdd", "application/vnd.stardivision.impress");
		mimeTypes.put("smf", "application/vnd.stardivision.math");
		mimeTypes.put("sdw", "application/vnd.stardivision.writer");
		mimeTypes.put("vor", "application/vnd.stardivision.writer");
		mimeTypes.put("sgl", "application/vnd.stardivision.writer-global");
		mimeTypes.put("sxc", "application/vnd.sun.xml.calc");
		mimeTypes.put("stc", "application/vnd.sun.xml.calc.template");
		mimeTypes.put("sxd", "application/vnd.sun.xml.draw");
		mimeTypes.put("std", "application/vnd.sun.xml.draw.template");
		mimeTypes.put("sxi", "application/vnd.sun.xml.impress");
		mimeTypes.put("sti", "application/vnd.sun.xml.impress.template");
		mimeTypes.put("sxm", "application/vnd.sun.xml.math");
		mimeTypes.put("sxw", "application/vnd.sun.xml.writer");
		mimeTypes.put("sxg", "application/vnd.sun.xml.writer.global");
		mimeTypes.put("stw", "application/vnd.sun.xml.writer.template");
		mimeTypes.put("sus", "application/vnd.sus-calendar");
		mimeTypes.put("susp", "application/vnd.sus-calendar");
		mimeTypes.put("svd", "application/vnd.svd");
		mimeTypes.put("sis", "application/vnd.symbian.install");
		mimeTypes.put("sisx", "application/vnd.symbian.install");
		mimeTypes.put("xsm", "application/vnd.syncml+xml");
		mimeTypes.put("bdm", "application/vnd.syncml.dm+wbxml");
		mimeTypes.put("xdm", "application/vnd.syncml.dm+xml");
		mimeTypes.put("tao", "application/vnd.tao.intent-module-archive");
		mimeTypes.put("tmo", "application/vnd.tmobile-livetv");
		mimeTypes.put("tpt", "application/vnd.trid.tpt");
		mimeTypes.put("mxs", "application/vnd.triscape.mxs");
		mimeTypes.put("tra", "application/vnd.trueapp");
		mimeTypes.put("ufd", "application/vnd.ufdl");
		mimeTypes.put("ufdl", "application/vnd.ufdl");
		mimeTypes.put("utz", "application/vnd.uiq.theme");
		mimeTypes.put("umj", "application/vnd.umajin");
		mimeTypes.put("unityweb", "application/vnd.unity");
		mimeTypes.put("uoml", "application/vnd.uoml+xml");
		mimeTypes.put("vcx", "application/vnd.vcx");
		mimeTypes.put("vsd", "application/vnd.visio");
		mimeTypes.put("vst", "application/vnd.visio");
		mimeTypes.put("vss", "application/vnd.visio");
		mimeTypes.put("vsw", "application/vnd.visio");
		mimeTypes.put("vis", "application/vnd.visionary");
		mimeTypes.put("vsf", "application/vnd.vsf");
		mimeTypes.put("wbxml", "application/vnd.wap.wbxml");
		mimeTypes.put("wmlc", "application/vnd.wap.wmlc");
		mimeTypes.put("wmlsc", "application/vnd.wap.wmlscriptc");
		mimeTypes.put("wtb", "application/vnd.webturbo");
		mimeTypes.put("nbp", "application/vnd.wolfram.player");
		mimeTypes.put("wpd", "application/vnd.wordperfect");
		mimeTypes.put("wqd", "application/vnd.wqd");
		mimeTypes.put("stf", "application/vnd.wt.stf");
		mimeTypes.put("xar", "application/vnd.xara");
		mimeTypes.put("xfdl", "application/vnd.xfdl");
		mimeTypes.put("hvd", "application/vnd.yamaha.hv-dic");
		mimeTypes.put("hvs", "application/vnd.yamaha.hv-script");
		mimeTypes.put("hvp", "application/vnd.yamaha.hv-voice");
		mimeTypes.put("osf", "application/vnd.yamaha.openscoreformat");
		mimeTypes.put("osfpvg", "application/vnd.yamaha.openscoreformat.osfpvg+xml");
		mimeTypes.put("saf", "application/vnd.yamaha.smaf-audio");
		mimeTypes.put("spf", "application/vnd.yamaha.smaf-phrase");
		mimeTypes.put("cmp", "application/vnd.yellowriver-custom-menu");
		mimeTypes.put("zir", "application/vnd.zul");
		mimeTypes.put("zirz", "application/vnd.zul");
		mimeTypes.put("zaz", "application/vnd.zzazz.deck+xml");
		mimeTypes.put("vxml", "application/voicexml+xml");
		mimeTypes.put("hlp", "application/winhlp");
		mimeTypes.put("wsdl", "application/wsdl+xml");
		mimeTypes.put("wspolicy", "application/wspolicy+xml");
		mimeTypes.put("abw", "application/x-abiword");
		mimeTypes.put("ace", "application/x-ace-compressed");
		mimeTypes.put("aab", "application/x-authorware-bin");
		mimeTypes.put("x32", "application/x-authorware-bin");
		mimeTypes.put("u32", "application/x-authorware-bin");
		mimeTypes.put("vox", "application/x-authorware-bin");
		mimeTypes.put("aam", "application/x-authorware-map");
		mimeTypes.put("aas", "application/x-authorware-seg");
		mimeTypes.put("bcpio", "application/x-bcpio");
		mimeTypes.put("torrent", "application/x-bittorrent");
		mimeTypes.put("bz", "application/x-bzip");
		mimeTypes.put("bz2", "application/x-bzip2");
		mimeTypes.put("boz", "application/x-bzip2");
		mimeTypes.put("vcd", "application/x-cdlink");
		mimeTypes.put("chat", "application/x-chat");
		mimeTypes.put("pgn", "application/x-chess-pgn");
		mimeTypes.put("cpio", "application/x-cpio");
		mimeTypes.put("csh", "application/x-csh");
		mimeTypes.put("deb", "application/x-debian-package");
		mimeTypes.put("udeb", "application/x-debian-package");
		mimeTypes.put("dir", "application/x-director");
		mimeTypes.put("dcr", "application/x-director");
		mimeTypes.put("dxr", "application/x-director");
		mimeTypes.put("cst", "application/x-director");
		mimeTypes.put("cct", "application/x-director");
		mimeTypes.put("cxt", "application/x-director");
		mimeTypes.put("w3d", "application/x-director");
		mimeTypes.put("fgd", "application/x-director");
		mimeTypes.put("swa", "application/x-director");
		mimeTypes.put("wad", "application/x-doom");
		mimeTypes.put("ncx", "application/x-dtbncx+xml");
		mimeTypes.put("dtb", "application/x-dtbook+xml");
		mimeTypes.put("res", "application/x-dtbresource+xml");
		mimeTypes.put("dvi", "application/x-dvi");
		mimeTypes.put("bdf", "application/x-font-bdf");
		mimeTypes.put("gsf", "application/x-font-ghostscript");
		mimeTypes.put("psf", "application/x-font-linux-psf");
		mimeTypes.put("otf", "application/x-font-otf");
		mimeTypes.put("pcf", "application/x-font-pcf");
		mimeTypes.put("snf", "application/x-font-snf");
		mimeTypes.put("ttf", "application/x-font-ttf");
		mimeTypes.put("ttc", "application/x-font-ttf");
		mimeTypes.put("pfa", "application/x-font-type1");
		mimeTypes.put("pfb", "application/x-font-type1");
		mimeTypes.put("pfm", "application/x-font-type1");
		mimeTypes.put("afm", "application/x-font-type1");
		mimeTypes.put("spl", "application/x-futuresplash");
		mimeTypes.put("gnumeric", "application/x-gnumeric");
		mimeTypes.put("gtar", "application/x-gtar");
		mimeTypes.put("hdf", "application/x-hdf");
		mimeTypes.put("jnlp", "application/x-java-jnlp-file");
		mimeTypes.put("latex", "application/x-latex");
		mimeTypes.put("prc mobi", "application/x-mobipocket-ebook");
		mimeTypes.put("application", "application/x-ms-application");
		mimeTypes.put("wmd", "application/x-ms-wmd");
		mimeTypes.put("wmz", "application/x-ms-wmz");
		mimeTypes.put("xbap", "application/x-ms-xbap");
		mimeTypes.put("mdb", "application/x-msaccess");
		mimeTypes.put("obd", "application/x-msbinder");
		mimeTypes.put("crd", "application/x-mscardfile");
		mimeTypes.put("clp", "application/x-msclip");
		mimeTypes.put("exe", "application/x-msdownload");
		mimeTypes.put("dll", "application/x-msdownload");
		mimeTypes.put("com", "application/x-msdownload");
		mimeTypes.put("bat", "application/x-msdownload");
		mimeTypes.put("mvb", "application/x-msmediaview");
		mimeTypes.put("m13", "application/x-msmediaview");
		mimeTypes.put("m14", "application/x-msmediaview");
		mimeTypes.put("wmf", "application/x-msmetafile");
		mimeTypes.put("mny", "application/x-msmoney");
		mimeTypes.put("pub", "application/x-mspublisher");
		mimeTypes.put("scd", "application/x-msschedule");
		mimeTypes.put("trm", "application/x-msterminal");
		mimeTypes.put("wri", "application/x-mswrite");
		mimeTypes.put("nc", "application/x-netcdf");
		mimeTypes.put("cdf", "application/x-netcdf");
		mimeTypes.put("p12", "application/x-pkcs12");
		mimeTypes.put("pfx", "application/x-pkcs12");
		mimeTypes.put("p7b", "application/x-pkcs7-certificates");
		mimeTypes.put("spc", "application/x-pkcs7-certificates");
		mimeTypes.put("p7r", "application/x-pkcs7-certreqresp");
		mimeTypes.put("rar", "application/x-rar-compressed");
		mimeTypes.put("sh", "application/x-sh");
		mimeTypes.put("shar", "application/x-shar");
		mimeTypes.put("swf", "application/x-shockwave-flash");
		mimeTypes.put("xap", "application/x-silverlight-app");
		mimeTypes.put("sit", "application/x-stuffit");
		mimeTypes.put("sitx", "application/x-stuffitx");
		mimeTypes.put("sv4cpio", "application/x-sv4cpio");
		mimeTypes.put("sv4crc", "application/x-sv4crc");
		mimeTypes.put("tar", "application/x-tar");
		mimeTypes.put("tcl", "application/x-tcl");
		mimeTypes.put("tex", "application/x-tex");
		mimeTypes.put("tfm", "application/x-tex-tfm");
		mimeTypes.put("texinfo", "application/x-texinfo");
		mimeTypes.put("texi", "application/x-texinfo");
		mimeTypes.put("ustar", "application/x-ustar");
		mimeTypes.put("src", "application/x-wais-source");
		mimeTypes.put("der", "application/x-x509-ca-cert");
		mimeTypes.put("crt", "application/x-x509-ca-cert");
		mimeTypes.put("fig", "application/x-xfig");
		mimeTypes.put("xpi", "application/x-xpinstall");
		mimeTypes.put("xenc", "application/xenc+xml");
		mimeTypes.put("xhtml", "application/xhtml+xml");
		mimeTypes.put("xht", "application/xhtml+xml");
		mimeTypes.put("xml", "application/xml");
		mimeTypes.put("xsl", "application/xml");
		mimeTypes.put("dtd", "application/xml-dtd");
		mimeTypes.put("xop", "application/xop+xml");
		mimeTypes.put("xslt", "application/xslt+xml");
		mimeTypes.put("xspf", "application/xspf+xml");
		mimeTypes.put("mxml", "application/xv+xml");
		mimeTypes.put("xhvml", "application/xv+xml");
		mimeTypes.put("xvml", "application/xv+xml");
		mimeTypes.put("xvm", "application/xv+xml");
		mimeTypes.put("zip", "application/zip");
		mimeTypes.put("adp", "audio/adpcm");
		mimeTypes.put("au", "audio/basic");
		mimeTypes.put("snd", "audio/basic");
		mimeTypes.put("mid", "audio/midi");
		mimeTypes.put("midi", "audio/midi");
		mimeTypes.put("kar", "audio/midi");
		mimeTypes.put("rmi", "audio/midi");
		mimeTypes.put("mp4a", "audio/mp4");
		mimeTypes.put("mpga", "audio/mpeg");
		mimeTypes.put("mp2", "audio/mpeg");
		mimeTypes.put("mp2a", "audio/mpeg");
		mimeTypes.put("mp3", "audio/mpeg");
		mimeTypes.put("m2a", "audio/mpeg");
		mimeTypes.put("m3a", "audio/mpeg");
		mimeTypes.put("oga", "audio/ogg");
		mimeTypes.put("ogg", "audio/ogg");
		mimeTypes.put("spx", "audio/ogg");
		mimeTypes.put("eol", "audio/vnd.digital-winds");
		mimeTypes.put("dra", "audio/vnd.dra");
		mimeTypes.put("dts", "audio/vnd.dts");
		mimeTypes.put("dtshd", "audio/vnd.dts.hd");
		mimeTypes.put("lvp", "audio/vnd.lucent.voice");
		mimeTypes.put("pya", "audio/vnd.ms-playready.media.pya");
		mimeTypes.put("ecelp4800", "audio/vnd.nuera.ecelp4800");
		mimeTypes.put("ecelp7470", "audio/vnd.nuera.ecelp7470");
		mimeTypes.put("ecelp9600", "audio/vnd.nuera.ecelp9600");
		mimeTypes.put("aac", "audio/x-aac");
		mimeTypes.put("aif", "audio/x-aiff");
		mimeTypes.put("aiff", "audio/x-aiff");
		mimeTypes.put("aifc", "audio/x-aiff");
		mimeTypes.put("m3u", "audio/x-mpegurl");
		mimeTypes.put("wax", "audio/x-ms-wax");
		mimeTypes.put("wma", "audio/x-ms-wma");
		mimeTypes.put("ram", "audio/x-pn-realaudio");
		mimeTypes.put("ra", "audio/x-pn-realaudio");
		mimeTypes.put("rmp", "audio/x-pn-realaudio-plugin");
		mimeTypes.put("wav", "audio/x-wav");
		mimeTypes.put("cdx", "chemical/x-cdx");
		mimeTypes.put("cif", "chemical/x-cif");
		mimeTypes.put("cmdf", "chemical/x-cmdf");
		mimeTypes.put("cml", "chemical/x-cml");
		mimeTypes.put("csml", "chemical/x-csml");
		mimeTypes.put("xyz", "chemical/x-xyz");
		mimeTypes.put("bmp", "image/bmp");
		mimeTypes.put("cgm", "image/cgm");
		mimeTypes.put("g3", "image/g3fax");
		mimeTypes.put("gif", "image/gif");
		mimeTypes.put("ief", "image/ief");
		mimeTypes.put("jpeg", "image/jpeg");
		mimeTypes.put("jpg", "image/jpeg");
		mimeTypes.put("jpe", "image/jpeg");
		mimeTypes.put("png", "image/png");
		mimeTypes.put("btif", "image/prs.btif");
		mimeTypes.put("svg", "image/svg+xml");
		mimeTypes.put("svgz", "image/svg+xml");
		mimeTypes.put("tiff", "image/tiff");
		mimeTypes.put("tif", "image/tiff");
		mimeTypes.put("psd", "image/vnd.adobe.photoshop");
		mimeTypes.put("djvu", "image/vnd.djvu");
		mimeTypes.put("djv", "image/vnd.djvu");
		mimeTypes.put("dwg", "image/vnd.dwg");
		mimeTypes.put("dxf", "image/vnd.dxf");
		mimeTypes.put("fbs", "image/vnd.fastbidsheet");
		mimeTypes.put("fpx", "image/vnd.fpx");
		mimeTypes.put("fst", "image/vnd.fst");
		mimeTypes.put("mmr", "image/vnd.fujixerox.edmics-mmr");
		mimeTypes.put("rlc", "image/vnd.fujixerox.edmics-rlc");
		mimeTypes.put("mdi", "image/vnd.ms-modi");
		mimeTypes.put("npx", "image/vnd.net-fpx");
		mimeTypes.put("wbmp", "image/vnd.wap.wbmp");
		mimeTypes.put("xif", "image/vnd.xiff");
		mimeTypes.put("ras", "image/x-cmu-raster");
		mimeTypes.put("cmx", "image/x-cmx");
		mimeTypes.put("fh", "image/x-freehand");
		mimeTypes.put("fhc", "image/x-freehand");
		mimeTypes.put("fh4", "image/x-freehand");
		mimeTypes.put("fh5", "image/x-freehand");
		mimeTypes.put("fh7", "image/x-freehand");
		mimeTypes.put("ico", "image/x-icon");
		mimeTypes.put("pcx", "image/x-pcx");
		mimeTypes.put("pic", "image/x-pict");
		mimeTypes.put("pct", "image/x-pict");
		mimeTypes.put("pnm", "image/x-portable-anymap");
		mimeTypes.put("pbm", "image/x-portable-bitmap");
		mimeTypes.put("pgm", "image/x-portable-graymap");
		mimeTypes.put("ppm", "image/x-portable-pixmap");
		mimeTypes.put("rgb", "image/x-rgb");
		mimeTypes.put("xbm", "image/x-xbitmap");
		mimeTypes.put("xpm", "image/x-xpixmap");
		mimeTypes.put("xwd", "image/x-xwindowdump");
		mimeTypes.put("eml", "message/rfc822");
		mimeTypes.put("mime", "message/rfc822");
		mimeTypes.put("igs", "model/iges");
		mimeTypes.put("iges", "model/iges");
		mimeTypes.put("msh", "model/mesh");
		mimeTypes.put("mesh", "model/mesh");
		mimeTypes.put("silo", "model/mesh");
		mimeTypes.put("dwf", "model/vnd.dwf");
		mimeTypes.put("gdl", "model/vnd.gdl");
		mimeTypes.put("gtw", "model/vnd.gtw");
		mimeTypes.put("mts", "model/vnd.mts");
		mimeTypes.put("vtu", "model/vnd.vtu");
		mimeTypes.put("wrl", "model/vrml");
		mimeTypes.put("vrml", "model/vrml");
		mimeTypes.put("ics", "text/calendar");
		mimeTypes.put("ifb", "text/calendar");
		mimeTypes.put("css", "text/css");
		mimeTypes.put("csv", "text/csv");
		mimeTypes.put("html", "text/html");
		mimeTypes.put("htm", "text/html");
		mimeTypes.put("txt", "text/plain");
		mimeTypes.put("text", "text/plain");
		mimeTypes.put("conf", "text/plain");
		mimeTypes.put("def", "text/plain");
		mimeTypes.put("list", "text/plain");
		mimeTypes.put("log", "text/plain");
		mimeTypes.put("in", "text/plain");
		mimeTypes.put("dsc", "text/prs.lines.tag");
		mimeTypes.put("rtx", "text/richtext");
		mimeTypes.put("sgml", "text/sgml");
		mimeTypes.put("sgm", "text/sgml");
		mimeTypes.put("tsv", "text/tab-separated-values");
		mimeTypes.put("t", "text/troff");
		mimeTypes.put("tr", "text/troff");
		mimeTypes.put("roff", "text/troff");
		mimeTypes.put("man", "text/troff");
		mimeTypes.put("me", "text/troff");
		mimeTypes.put("ms", "text/troff");
		mimeTypes.put("uri", "text/uri-list");
		mimeTypes.put("uris", "text/uri-list");
		mimeTypes.put("urls", "text/uri-list");
		mimeTypes.put("curl", "text/vnd.curl");
		mimeTypes.put("dcurl", "text/vnd.curl.dcurl");
		mimeTypes.put("scurl", "text/vnd.curl.scurl");
		mimeTypes.put("mcurl", "text/vnd.curl.mcurl");
		mimeTypes.put("fly", "text/vnd.fly");
		mimeTypes.put("flx", "text/vnd.fmi.flexstor");
		mimeTypes.put("gv", "text/vnd.graphviz");
		mimeTypes.put("3dml", "text/vnd.in3d.3dml");
		mimeTypes.put("spot", "text/vnd.in3d.spot");
		mimeTypes.put("jad", "text/vnd.sun.j2me.app-descriptor");
		mimeTypes.put("wml", "text/vnd.wap.wml");
		mimeTypes.put("wmls", "text/vnd.wap.wmlscript");
		mimeTypes.put("s", "text/x-asm");
		mimeTypes.put("asm", "text/x-asm");
		mimeTypes.put("c", "text/x-c");
		mimeTypes.put("cc", "text/x-c");
		mimeTypes.put("cxx", "text/x-c");
		mimeTypes.put("cxx", "text/x-c");
		mimeTypes.put("cpp", "text/x-c");
		mimeTypes.put("h", "text/x-c");
		mimeTypes.put("hh", "text/x-c");
		mimeTypes.put("dic", "text/x-c");
		mimeTypes.put("f", "text/x-fortran");
		mimeTypes.put("for", "text/x-fortran");
		mimeTypes.put("f77", "text/x-fortran");
		mimeTypes.put("f90", "text/x-fortran");
		mimeTypes.put("p", "text/x-pascal");
		mimeTypes.put("pas", "text/x-pascal");
		mimeTypes.put("java", "text/x-java-source");
		mimeTypes.put("etx", "text/x-setext");
		mimeTypes.put("uu", "text/x-uuencode");
		mimeTypes.put("vcs", "text/x-vcalendar");
		mimeTypes.put("vcf", "text/x-vcard");
		mimeTypes.put("3gp", "video/3gpp");
		mimeTypes.put("3g2", "video/3gpp2");
		mimeTypes.put("h261", "video/h261");
		mimeTypes.put("h263", "video/h263");
		mimeTypes.put("h264", "video/h264");
		mimeTypes.put("jpgv", "video/jpeg");
		mimeTypes.put("jpm", "video/jpm");
		mimeTypes.put("jpgm", "video/jpm");
		mimeTypes.put("mj2", "video/mj2");
		mimeTypes.put("mjp2", "video/mj2");
		mimeTypes.put("mp4", "video/mp4");
		mimeTypes.put("mp4v", "video/mp4");
		mimeTypes.put("mpg4", "video/mp4");
		mimeTypes.put("mpeg", "video/mpeg");
		mimeTypes.put("mpg", "video/mpeg");
		mimeTypes.put("mpe", "video/mpeg");
		mimeTypes.put("m1v", "video/mpeg");
		mimeTypes.put("m2v", "video/mpeg");
		mimeTypes.put("ogv", "video/ogg");
		mimeTypes.put("qt", "video/quicktime");
		mimeTypes.put("mov", "video/quicktime");
		mimeTypes.put("fvt", "video/vnd.fvt");
		mimeTypes.put("mxu", "video/vnd.mpegurl");
		mimeTypes.put("m4u", "video/vnd.mpegurl");
		mimeTypes.put("pyv", "video/vnd.ms-playready.media.pyv");
		mimeTypes.put("viv", "video/vnd.vivo");
		mimeTypes.put("f4v", "video/x-f4v");
		mimeTypes.put("fli", "video/x-fli");
		mimeTypes.put("flv", "video/x-flv");
		mimeTypes.put("m4v", "video/x-m4v");
		mimeTypes.put("asf", "video/x-ms-asf");
		mimeTypes.put("asx", "video/x-ms-asf");
		mimeTypes.put("wm", "video/x-ms-wm");
		mimeTypes.put("wmv", "video/x-ms-wmv");
		mimeTypes.put("wmx", "video/x-ms-wmx");
		mimeTypes.put("wvx", "video/x-ms-wvx");
		mimeTypes.put("avi", "video/x-msvideo");
		mimeTypes.put("movie", "video/x-sgi-movie");
		mimeTypes.put("ice", "x-conference/x-cooltalk");
	}
	
	/**
	 * Root folder for of shared resources by the server.
	 * 
	 */
	private String root = "/"; // TODO: Make it more configurable.
	
	/**
	 * Location of the represented resource in the root.
	 * This location can't represents a file out of the root.
	 * 
	 */
	private String uri = "/";
	
	public Resource() {
		
	}
	
	public Resource(String uri) {
		setUri(uri);
	}

	public void setUri(String uri) {
		if (uri == null) {
			throw new NullPointerException("URI can't be null");
		}
		
		if (uri.length() > 0 && uri.charAt(0) != '/') {
			uri = "/" + uri;
		} else if (uri.length() == 0) {
			uri = "/";
		}
		
		this.uri = uri;
	}
	
	public String getUri() {
		return uri;
	}
	
	/**
	 * Append new segment to the represented URI.
	 * 
	 * @param segment
	 */
	public void addSegment(String segment) {
		if (segment == null) {
			throw new NullPointerException("Segment can't be null.");
		}

		segment = ltrim(segment);
		uri = rtrim(uri);
		
		uri = uri + "/" + segment;
	}
	
	/**
	 * Removes last URI segment and returns it.
	 * 
	 * @return Removed segment value.
	 */
	public String removeLastSegment() {
		String segment = null;
		int i;
		
		uri = rtrim(uri);
		
		i = uri.lastIndexOf('/');
		
		if (i >= 0) {
			segment = uri.substring(i + 1);
			uri = uri.substring(0, i);
		}
		
		if (uri.length() == 0) {
			uri = "/";
		}
		
		return segment;
	}
	
	/**
	 * Remove leading slashes.
	 * 
	 * @param str
	 * @return
	 */
	private String ltrim(String str) {
		if (str == null) {
			throw new NullPointerException();
		}
		
		while (str.length() > 0 && str.charAt(0) == '/') {
			str = str.substring(1);
		}
		
		return str;
	}
	
	/**
	 * Remove slashes at the end of the given string.
	 * 
	 * @param str
	 * @return
	 */
	private String rtrim(String str) {
		if (str == null) {
			throw new NullPointerException();
		}

		for (int i = str.length() - 1; i >= 0 && str.charAt(i) == '/'; i--) {
			str = str.substring(0, i);
		}
		
		return str;
	}
	
	/**
	 * Returns type of the resource.
	 * 
	 * @return
	 */
	public ResourceType getType() {
		File file = new File(rtrim(root) + uri);
		
		if (!file.exists()) {
			return ResourceType.NOT_EXISTS;
		} else if (!file.canRead()) {
			return ResourceType.NOT_READABLE;
		} else if (file.isDirectory()) {
			return ResourceType.DIRECTORY;
		} else {
			return ResourceType.FILE;
		}
	}
	
	public long getSize() {
		return new File(rtrim(root) + uri).length();
	}
	
	/**
	 * Returns full (from the root of the file system) path to the resource.
	 * 
	 * @return
	 */
	public String getPath() {
		return rtrim(root) + uri;
	}
	
	public String getName() {
		Resource res = null;
		
		try {
			res = this.clone();
		} catch (CloneNotSupportedException e) {
			// Leave it.
		}
		
		return res.removeLastSegment();
	}

	/**
	 * Returns extension of the file associated with URI, or null in other cases.
	 * 
	 * @return
	 */
	public String getExtension() {
		String filename = new File(uri).getName();
		
		if (filename.lastIndexOf(".") == -1) {
			return null;
		} else {
			return filename.substring(filename.lastIndexOf(".") + 1);
		}
	}
	
	public String getMimeType() {
		String extension = getExtension();
		
		if (extension == null) {
			return DEFAULT_MIME_TYPE;
		}
		
		String mimeType = mimeTypes.get(extension.toLowerCase()); 
		
		if (mimeType != null) {
			return mimeType;
		} else {
			return DEFAULT_MIME_TYPE;
		}
	}
	
	public BufferedReader getReader() throws FileNotFoundException {
		return new BufferedReader(new FileReader(new File(getPath())));
	}
	
	@Override
	public Resource clone() throws CloneNotSupportedException {
		Resource cloned = (Resource) super.clone();
		cloned.root = root;
		cloned.uri = uri;
		
		return cloned;
	}
}
