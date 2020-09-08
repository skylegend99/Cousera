# # Tags: indicate the beginning and ending of elements
# # Attributes: Keyword/value pairs on the opening tag of XML
# # Serialize/De-Serialize: Convert data in one program into a commom format that can be store or transmitted between systems in a programming language independent manner
# Format of XML
# <a>   #beginning of the tag
#     <b>X</b>
#     <c>
#         <d>Y</d>
#         <e>Z</e>
#     </c>
# </a>  #ending of the tag

import xml.etree.ElementTree as ET

#First example:
#String data '''
data = '''
<person>
    <name>Chuck</name>
    <phone type = "int1">
        +1 734 303 4456
       </phone>
       <email hide = "yes"/>
</person>'''
tree = ET.fromstring(data)  # Get back a tree information that correctly parsed
print('Name:', tree.find('name').text) # Gives back "Chuck"
print('Attr:', tree.find('email').get('hide')) # Gives back "yes"

# Second example:

input = '''
<stuff>
    <users>
        <user x="2">
            <id>001</id>
            <name>Chuck</name>
        </user>
        <user x="7">
            <id>009</id>
            <name>Brent</name>
            </user>
        </users>
</stuff>'''

stuff = ET.fromstring(input)
lst = stuff.findall('users/user')
print('User count:', len(lst))

for item in lst:
    print('Name', item.find('name').text)
    print('Id', item.find('id').text)
    print('Attribute', item.get("x"))
