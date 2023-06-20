REGISTER s3n://dataelm2/dependencies/elm.jar;
REGISTER s3n://dataelm2/dependencies/weka.jar;
REGISTER s3n://dataelm2/dependencies/aws.jar;
REGISTER s3n://dataelm2/dependencies/pig.jar;
REGISTER s3n://dataelm2/dependencies/mtj.jar;
REGISTER s3n://dataelm2/dependencies/core.jar;
REGISTER s3n://dataelm2/dependencies/arpackcombinedall.jar
IMPORT 's3n://dataelm2/pigimportelm.pig';

data1 = LOAD '$input' USING org.apache.pig.piggybank.storage.CSVExcelStorage(' ', 'YES_MULTILINE', 'NOCHANGE') AS (labels:int,train0:float,train1:float,train2:float,train3:float,train4:float,train5:float,train6:float,train7:float,train8:float,train9:float,train10:float,train11:float,train12:float,train13:float,train14:float,train15:float,train16:float,train17:float,train18:float,train19:float,train20:float,train21:float,train22:float,train23:float,train24:float,train25:float,train26:float,train27:float,train28:float,train29:float,train30:float,train31:float,train32:float,train33:float,train34:float,train35:float,train36:float,train37:float,train38:float,train39:float,train40:float,train41:float,train42:float,train43:float,train44:float,train45:float,train46:float,train47:float,train48:float,train49:float,train50:float,train51:float,train52:float,train53:float,train54:float,train55:float,train56:float,train57:float,train58:float,train59:float,train60:float,train61:float,train62:float,train63:float,train64:float,train65:float,train66:float,train67:float,train68:float,train69:float,train70:float,train71:float,train72:float,train73:float,train74:float,train75:float,train76:float,train77:float,train78:float,train79:float,train80:float,train81:float,train82:float,train83:float,train84:float,train85:float,train86:float,train87:float,train88:float,train89:float,train90:float,train91:float,train92:float,train93:float,train94:float,train95:float,train96:float,train97:float,train98:float,train99:float,train100:float,train101:float,train102:float,train103:float,train104:float,train105:float,train106:float,train107:float,train108:float,train109:float,train110:float,train111:float,train112:float,train113:float,train114:float,train115:float,train116:float,train117:float,train118:float,train119:float,train120:float,train121:float,train122:float,train123:float,train124:float,train125:float,train126:float,train127:float,train128:float,train129:float,train130:float,train131:float,train132:float,train133:float,train134:float,train135:float,train136:float,train137:float,train138:float,train139:float,train140:float,train141:float,train142:float,train143:float,train144:float,train145:float,train146:float,train147:float,train148:float,train149:float,train150:float,train151:float,train152:float,train153:float,train154:float,train155:float,train156:float,train157:float,train158:float,train159:float,train160:float,train161:float,train162:float,train163:float,train164:float,train165:float,train166:float,train167:float,train168:float,train169:float,train170:float,train171:float,train172:float,train173:float,train174:float,train175:float,train176:float,train177:float,train178:float,train179:float,train180:float,train181:float,train182:float,train183:float,train184:float,train185:float,train186:float,train187:float,train188:float,train189:float,train190:float,train191:float,train192:float,train193:float,train194:float,train195:float,train196:float,train197:float,train198:float,train199:float); 

data2 = LOAD '$input' USING org.apache.pig.piggybank.storage.CSVExcelStorage(' ', 'YES_MULTILINE', 'NOCHANGE') AS (labels:int,train0:float,train1:float,train2:float,train3:float,train4:float,train5:float,train6:float,train7:float,train8:float,train9:float,train10:float,train11:float,train12:float,train13:float,train14:float,train15:float,train16:float,train17:float,train18:float,train19:float,train20:float,train21:float,train22:float,train23:float,train24:float,train25:float,train26:float,train27:float,train28:float,train29:float,train30:float,train31:float,train32:float,train33:float,train34:float,train35:float,train36:float,train37:float,train38:float,train39:float,train40:float,train41:float,train42:float,train43:float,train44:float,train45:float,train46:float,train47:float,train48:float,train49:float,train50:float,train51:float,train52:float,train53:float,train54:float,train55:float,train56:float,train57:float,train58:float,train59:float,train60:float,train61:float,train62:float,train63:float,train64:float,train65:float,train66:float,train67:float,train68:float,train69:float,train70:float,train71:float,train72:float,train73:float,train74:float,train75:float,train76:float,train77:float,train78:float,train79:float,train80:float,train81:float,train82:float,train83:float,train84:float,train85:float,train86:float,train87:float,train88:float,train89:float,train90:float,train91:float,train92:float,train93:float,train94:float,train95:float,train96:float,train97:float,train98:float,train99:float,train100:float,train101:float,train102:float,train103:float,train104:float,train105:float,train106:float,train107:float,train108:float,train109:float,train110:float,train111:float,train112:float,train113:float,train114:float,train115:float,train116:float,train117:float,train118:float,train119:float,train120:float,train121:float,train122:float,train123:float,train124:float,train125:float,train126:float,train127:float,train128:float,train129:float,train130:float,train131:float,train132:float,train133:float,train134:float,train135:float,train136:float,train137:float,train138:float,train139:float,train140:float,train141:float,train142:float,train143:float,train144:float,train145:float,train146:float,train147:float,train148:float,train149:float,train150:float,train151:float,train152:float,train153:float,train154:float,train155:float,train156:float,train157:float,train158:float,train159:float,train160:float,train161:float,train162:float,train163:float,train164:float,train165:float,train166:float,train167:float,train168:float,train169:float,train170:float,train171:float,train172:float,train173:float,train174:float,train175:float,train176:float,train177:float,train178:float,train179:float,train180:float,train181:float,train182:float,train183:float,train184:float,train185:float,train186:float,train187:float,train188:float,train189:float,train190:float,train191:float,train192:float,train193:float,train194:float,train195:float,train196:float,train197:float,train198:float,train199:float); 

data3 = LOAD '$input' USING org.apache.pig.piggybank.storage.CSVExcelStorage(' ', 'YES_MULTILINE', 'NOCHANGE') AS (labels:int,train0:float,train1:float,train2:float,train3:float,train4:float,train5:float,train6:float,train7:float,train8:float,train9:float,train10:float,train11:float,train12:float,train13:float,train14:float,train15:float,train16:float,train17:float,train18:float,train19:float,train20:float,train21:float,train22:float,train23:float,train24:float,train25:float,train26:float,train27:float,train28:float,train29:float,train30:float,train31:float,train32:float,train33:float,train34:float,train35:float,train36:float,train37:float,train38:float,train39:float,train40:float,train41:float,train42:float,train43:float,train44:float,train45:float,train46:float,train47:float,train48:float,train49:float,train50:float,train51:float,train52:float,train53:float,train54:float,train55:float,train56:float,train57:float,train58:float,train59:float,train60:float,train61:float,train62:float,train63:float,train64:float,train65:float,train66:float,train67:float,train68:float,train69:float,train70:float,train71:float,train72:float,train73:float,train74:float,train75:float,train76:float,train77:float,train78:float,train79:float,train80:float,train81:float,train82:float,train83:float,train84:float,train85:float,train86:float,train87:float,train88:float,train89:float,train90:float,train91:float,train92:float,train93:float,train94:float,train95:float,train96:float,train97:float,train98:float,train99:float,train100:float,train101:float,train102:float,train103:float,train104:float,train105:float,train106:float,train107:float,train108:float,train109:float,train110:float,train111:float,train112:float,train113:float,train114:float,train115:float,train116:float,train117:float,train118:float,train119:float,train120:float,train121:float,train122:float,train123:float,train124:float,train125:float,train126:float,train127:float,train128:float,train129:float,train130:float,train131:float,train132:float,train133:float,train134:float,train135:float,train136:float,train137:float,train138:float,train139:float,train140:float,train141:float,train142:float,train143:float,train144:float,train145:float,train146:float,train147:float,train148:float,train149:float,train150:float,train151:float,train152:float,train153:float,train154:float,train155:float,train156:float,train157:float,train158:float,train159:float,train160:float,train161:float,train162:float,train163:float,train164:float,train165:float,train166:float,train167:float,train168:float,train169:float,train170:float,train171:float,train172:float,train173:float,train174:float,train175:float,train176:float,train177:float,train178:float,train179:float,train180:float,train181:float,train182:float,train183:float,train184:float,train185:float,train186:float,train187:float,train188:float,train189:float,train190:float,train191:float,train192:float,train193:float,train194:float,train195:float,train196:float,train197:float,train198:float,train199:float); 

data4 = LOAD '$input' USING org.apache.pig.piggybank.storage.CSVExcelStorage(' ', 'YES_MULTILINE', 'NOCHANGE') AS (labels:int,train0:float,train1:float,train2:float,train3:float,train4:float,train5:float,train6:float,train7:float,train8:float,train9:float,train10:float,train11:float,train12:float,train13:float,train14:float,train15:float,train16:float,train17:float,train18:float,train19:float,train20:float,train21:float,train22:float,train23:float,train24:float,train25:float,train26:float,train27:float,train28:float,train29:float,train30:float,train31:float,train32:float,train33:float,train34:float,train35:float,train36:float,train37:float,train38:float,train39:float,train40:float,train41:float,train42:float,train43:float,train44:float,train45:float,train46:float,train47:float,train48:float,train49:float,train50:float,train51:float,train52:float,train53:float,train54:float,train55:float,train56:float,train57:float,train58:float,train59:float,train60:float,train61:float,train62:float,train63:float,train64:float,train65:float,train66:float,train67:float,train68:float,train69:float,train70:float,train71:float,train72:float,train73:float,train74:float,train75:float,train76:float,train77:float,train78:float,train79:float,train80:float,train81:float,train82:float,train83:float,train84:float,train85:float,train86:float,train87:float,train88:float,train89:float,train90:float,train91:float,train92:float,train93:float,train94:float,train95:float,train96:float,train97:float,train98:float,train99:float,train100:float,train101:float,train102:float,train103:float,train104:float,train105:float,train106:float,train107:float,train108:float,train109:float,train110:float,train111:float,train112:float,train113:float,train114:float,train115:float,train116:float,train117:float,train118:float,train119:float,train120:float,train121:float,train122:float,train123:float,train124:float,train125:float,train126:float,train127:float,train128:float,train129:float,train130:float,train131:float,train132:float,train133:float,train134:float,train135:float,train136:float,train137:float,train138:float,train139:float,train140:float,train141:float,train142:float,train143:float,train144:float,train145:float,train146:float,train147:float,train148:float,train149:float,train150:float,train151:float,train152:float,train153:float,train154:float,train155:float,train156:float,train157:float,train158:float,train159:float,train160:float,train161:float,train162:float,train163:float,train164:float,train165:float,train166:float,train167:float,train168:float,train169:float,train170:float,train171:float,train172:float,train173:float,train174:float,train175:float,train176:float,train177:float,train178:float,train179:float,train180:float,train181:float,train182:float,train183:float,train184:float,train185:float,train186:float,train187:float,train188:float,train189:float,train190:float,train191:float,train192:float,train193:float,train194:float,train195:float,train196:float,train197:float,train198:float,train199:float);

data1 = GROUP data1 ALL;
data2 = GROUP data2 ALL;
data3 = GROUP data3 ALL;
data4 = GROUP data4 ALL;

data1 = FOREACH data1 GENERATE $1 AS train_set;  
data2 = FOREACH data2 GENERATE $1 AS train_set; 
data3 = FOREACH data3 GENERATE $1 AS train_set;  
data4 = FOREACH data4 GENERATE $1 AS train_set; 

data = UNION data1, data2;
data = UNION data, data3;
data = UNION data, data4;

out = FOREACH data GENERATE II_ELM(1,$n_hidden,'sig',58,1,$seed,train_set);

out = FOREACH out GENERATE REPLACE($0, '[\\\'\\[\\]]+', '');

STORE out INTO '$output' USING PigStorage(',');
