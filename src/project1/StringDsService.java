package project1;

import models.StringDs;

public interface StringDsService {

    char[] add(char character,StringDs stringDs);

    StringDs getInputChars();

    void print(StringDs stringDs);

    int[] findPositionsOfChar(StringDs stringDs,char searchChar);

    int findFirstPositionOfChar(StringDs stringDs, char searchChar);

    int findSubarrayPosition(StringDs stringDs,StringDs subString);

    char[] produceSubArray(StringDs stringDs,int start,int end);

    char[] deleteSubArrayByIndex(StringDs stringDs,int start,int end);

    char[] lcs(StringDs stringDs1,StringDs stringDs2);



}
